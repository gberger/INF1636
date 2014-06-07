package com.monopoly.engine.core;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.monopoly.engine.cards.Card;
import com.monopoly.engine.cards.CompanyDeck;
import com.monopoly.engine.cards.NegotiableCard;
import com.monopoly.engine.cards.PropertyCard;
import com.monopoly.engine.cards.TerrainCard;
import com.monopoly.engine.cards.TerrainDeck;
import com.monopoly.engine.cards.chancecards.ChanceDeck;
import com.monopoly.ui.UserInterface;
import com.monopoly.ui.UserInterfaceEvents;
import com.monopoly.ui.UserInterfaceNotification;

public class Game implements Observer {
  
  private Board board;
  private ChanceDeck chanceDeck;
  private CompanyDeck companyDeck;
  private TerrainDeck terrainDeck;
  private List<Player> players;
  private int currentPlayerIndex;
  private UserInterface ui;
  private boolean hasCurrPlayerPlayed = false;
  private Bank bank;
  
  private void initializeChanceDeck() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/chance.json"));
    
    this.chanceDeck = new ChanceDeck(arr, this);
    System.out.println("Initialized Chance Deck succesfully!");
  }
  
  private void initializeCompanyDeck() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/company.json"));
    
    this.companyDeck = new CompanyDeck(arr, this);
    System.out.println("Initialized Company Deck succesfully!");
  }
  
  private void initializeTerrainDeck() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/terrain.json"));
    
    this.terrainDeck = new TerrainDeck(arr, this);
    System.out.println("Initialized Terrain Deck succesfully!");
  }

  private void initializeBoard() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/board.json"));

    this.board = new Board(arr, this);
    System.out.println("Initialized Board succesfully!");
  }

  private void initializePlayers(int numPlayers) throws IllegalArgumentException {
    if(numPlayers < 2 || numPlayers > 6){
      throw new IllegalArgumentException("Number of players must be between 2 and 6 (inclusive).");
    }

    this.players = new ArrayList<Player>();

    for(int i = 0; i < numPlayers; i++){
      PlayerColor color = PlayerColor.values()[i];
      this.players.add(new Player(color, this));
    }
    this.currentPlayerIndex = 0;

    System.out.println("Initialized Players succesfully!");
  }
  
  public Game(int numPlayers){
    try {
      this.initializeChanceDeck();
      this.initializeCompanyDeck();
      this.initializeTerrainDeck();
      this.initializeBoard();
      this.initializePlayers(numPlayers);
      this.bank = new Bank();
    } catch (IOException | ParseException | IllegalArgumentException e) {
      e.printStackTrace();
      System.exit(1);
    }

    System.out.println("Initialized everything succesfully!");
  }

  public void bindUI(UserInterface ui) {
    this.ui = ui;
  }

  public UserInterface getUI() {
    return this.ui;
  }

  public Board getBoard() {
    return this.board;
  }

  public ChanceDeck getChanceDeck() {
    return this.chanceDeck;
  }

  public CompanyDeck getCompanyDeck() {
    return this.companyDeck;
  }

  public TerrainDeck getTerrainDeck() {
    return this.terrainDeck;
  }

  public List<Player> getPlayers() {
    return this.players;
  }

  public Bank getBank() {
    return this.bank;
  }

  public int getCurrentPlayerIndex() {
    return this.currentPlayerIndex;
  }

  public Player getCurrentPlayer() {
    return this.players.get(this.currentPlayerIndex);
  }

  public Player getCardOwner(Card card) {
    for(Player player : this.players) {
      if(player.getCards().contains(card))
        return player;
    }
    return (Player) null;
  }

  @Override
  public void update(Observable o, Object uin) {
    UserInterfaceNotification notification = (UserInterfaceNotification)uin;
    UserInterfaceEvents event = notification.event;
    Map<String, Object> args = notification.args;
    
    if(event == UserInterfaceEvents.ROLL_DICES) {
      if(this.validateRollDices()) {
        this.rollDices(); 
      } else {
        this.ui.showMessage("Ação proibida!");
      }
      
    } else if(event == UserInterfaceEvents.PASS_TURN) {
      if(this.validatePassTurn()) {
        this.passTurn(); 
      } else {
        this.ui.showMessage("Ação proibida!");
      }
      
    } else if(event == UserInterfaceEvents.JAIL_PASS) {
      if(this.validateUseJailPass()) {
        this.useJailPass(); 
      } else {
        this.ui.showMessage("Ação proibida!");
      }
      
    } else if(event == UserInterfaceEvents.GO_BANKRUPT) {
      if(this.validateGoBankrupt()) {
        this.goBankrupt(); 
      } else {
        this.ui.showMessage("Ação proibida!");
      }
    } else if(event == UserInterfaceEvents.CARD_VIEW) {
      PropertyCard card = (PropertyCard)args.get("card");
      this.ui.showMessage(card.getInfoText(), card.getName());
      
    } else if(event == UserInterfaceEvents.CARD_NEGOTIATE) {
      NegotiableCard card = (NegotiableCard)args.get("card");
      this.negotiateCard(card);
    } else if(event == UserInterfaceEvents.CARD_MORTGAGE) {
      PropertyCard card = (PropertyCard)args.get("card");
      
      
    } else if(event == UserInterfaceEvents.CARD_NEW_BUILDING) {
      TerrainCard card = (TerrainCard)args.get("card");
      if(this.validateNewBuilding(card)){
        this.newBuilding(card);
      } else {
        this.ui.showMessage("Ação proibida!");
      }
    } else if(event == UserInterfaceEvents.CARD_REMOVE_BULDING) {
      TerrainCard card = (TerrainCard)args.get("card");
      if(this.validateRemoveBuilding(card)){
        this.removeBuilding(card);
      } else {
        this.ui.showMessage("Ação proibida!");
      }
    }
  }

  private boolean validateRollDices() {
    if(this.hasCurrPlayerPlayed) {
      Player currPlayer = this.getCurrentPlayer();
      return currPlayer.goesAgain();
    } else {
      return true;
    }
  }

  private void rollDices() {
    Player currPlayer = this.getCurrentPlayer();
    currPlayer.roll();
    this.hasCurrPlayerPlayed = true;
  }
  
  private boolean validatePassTurn() {
    Player currPlayer = this.getCurrentPlayer();
    if(currPlayer.canGoBankrupt()){
      return false;
    } else if(this.hasCurrPlayerPlayed) {
      return !currPlayer.goesAgain();
    } else {
      return false;
    }
  }

  public void passTurn() {
    this.getCurrentPlayer().resetDoubleCounter();
    this.hasCurrPlayerPlayed = false;
    
    this.currentPlayerIndex += 1;
    this.currentPlayerIndex %= this.players.size();
    while(this.getCurrentPlayer().isOutOfGame()){
      this.currentPlayerIndex += 1;
      this.currentPlayerIndex %= this.players.size();
    }
  }

  private boolean validateUseJailPass() {
    Player currPlayer = this.getCurrentPlayer();
    return currPlayer.isInJail() && currPlayer.hasJailPass();
  }

  private void useJailPass() {
    Player currPlayer = this.getCurrentPlayer();
    currPlayer.useJailPass();
  }

  private boolean validateGoBankrupt() {
    Player currPlayer = this.getCurrentPlayer();
    return currPlayer.canGoBankrupt();
  }

  private void goBankrupt() {
    Player currPlayer = this.getCurrentPlayer();
    currPlayer.removeFromGame();
    this.passTurn();
    this.checkWinner();
  }
  
  private void negotiateCard(NegotiableCard card) {
    Player currPlayer = this.getCurrentPlayer();
    
    List<Entity> others = new ArrayList<Entity>();
    others.add(this.bank);
    for(Player p : this.players) {
      if(p != currPlayer && !p.isOutOfGame()) {
        others.add(p);
      }
    }
    
    Object[] options = others.toArray();
    Object choice = this.ui.askOptions("Com quem você quer negociar?", options);
  }

  private boolean validateNewBuilding(TerrainCard card) {
    Player currPlayer = this.getCurrentPlayer();
    List<TerrainCard> allCards = this.terrainDeck.findByColor(card.getColor());
    if(!currPlayer.ownsCard(card)){
      this.ui.showMessage("Voce nao eh o dono desta carta!");
      return false;
    }
    if(!currPlayer.ownsTerrainCards(allCards)) {
      this.ui.showMessage("Voce deve possuir todas as cartas dessa cor antes de constuir.");
      return false;
    }
    if(card.isFull()){
      this.ui.showMessage("Nao eh possivel construir mais construções neste terreno.");
      return false;
    }
    for(TerrainCard c : allCards){
      if(c.getBuildings() != card.getBuildings()){
        this.ui.showMessage("Voce deve antes construir mais construções nos outros terrenos de mesma cor.");
        return false;
      }
    }
    int cost = card.getNextBuildingCost();
    if(!currPlayer.affords(cost)){
      this.ui.showMessage("Dinheiro insuficiente, eh necessario $" + cost);
      return false;
    }
    return true;
  }

  private void newBuilding(TerrainCard card) {
    Player currPlayer = this.getCurrentPlayer();
    int cost = card.getNextBuildingCost();
    if(currPlayer.affords(cost) && card.addBuilding()){
      currPlayer.charge(cost);
      this.ui.showMessage(currPlayer.getName() + " gastou $" + cost + " em uma nova construcao em " + card.getName());
    } 
  }

  private boolean validateRemoveBuilding(TerrainCard card) {
    Player currPlayer = this.getCurrentPlayer();
    List<TerrainCard> allCards = this.terrainDeck.findByColor(card.getColor());
    if(!currPlayer.ownsCard(card)){
      this.ui.showMessage("Voce nao eh o dono desta carta!");
      return false;
    }
    if(card.isEmpty()){
      this.ui.showMessage("Não há construções neste terreno.");
      return false;
    }
    for(TerrainCard c : allCards){
      if(c.getBuildings() > card.getBuildings()){
        this.ui.showMessage("Você deve antes demolir construções em outros terrenos desta mesma cor.");
        return false;
      }
    }
    return true;
  }

  private void removeBuilding(TerrainCard card) {
    Player currPlayer = this.getCurrentPlayer();
    int earnings = card.getNextBuildingRemovalEarnings();
    if(card.removeBuilding()){
      currPlayer.give(earnings);
      this.ui.showMessage(currPlayer.getName() + " ganhou $" + earnings + " demolindo uma construcao em " + card.getName());
    } 
  }
  
  private void checkWinner(){
    int playersStillInGame = 0;
    Player winner = null;
    for(Player player : this.players){
      if(!player.isOutOfGame()){
        winner = player;
        playersStillInGame++;
      }
    }
    
    if(playersStillInGame == 1){
      this.ui.showMessage("O GANHADOR EH " + winner.getName(), "FIM DE JOGO");
      System.exit(0);
    }
  }
  
}

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

import com.monopoly.engine.cards.CompanyDeck;
import com.monopoly.engine.cards.NegotiableCard;
import com.monopoly.engine.cards.PropertyCard;
import com.monopoly.engine.cards.TerrainCard;
import com.monopoly.engine.cards.TerrainDeck;
import com.monopoly.engine.cards.chancecards.ChanceDeck;
import com.monopoly.engine.squares.PropertySquare;
import com.monopoly.engine.squares.Square;
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
    JSONArray arr = (JSONArray) parser
        .parse(new FileReader("data/chance.json"));

    this.chanceDeck = new ChanceDeck(arr, this);
    System.out.println("Initialized Chance Deck succesfully!");
  }

  private void initializeCompanyDeck() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser
        .parse(new FileReader("data/company.json"));

    this.companyDeck = new CompanyDeck(arr, this);
    System.out.println("Initialized Company Deck succesfully!");
  }

  private void initializeTerrainDeck() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser
        .parse(new FileReader("data/terrain.json"));

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
    if (numPlayers < 2 || numPlayers > 6) {
      throw new IllegalArgumentException(
          "Number of players must be between 2 and 6 (inclusive).");
    }

    this.players = new ArrayList<Player>();

    for (int i = 0; i < numPlayers; i++) {
      PlayerColor color = PlayerColor.values()[i];
      this.players.add(new Player(color, this));
    }
    this.currentPlayerIndex = 0;

    System.out.println("Initialized Players succesfully!");
  }

  public Game(int numPlayers) {
    try {
      this.bank = new Bank();
      this.initializeChanceDeck();
      this.initializeCompanyDeck();
      this.initializeTerrainDeck();
      this.initializeBoard();
      this.initializePlayers(numPlayers);
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

  public List<Player> getRemainingPlayers() {
    List<Player> rem = new ArrayList<Player>();
    for (Player p : this.players) {
      if (!p.isOutOfGame()) {
        rem.add(p);
      }
    }
    return rem;
  }

  public Bank getBank() {
    return this.bank;
  }

  public Player getCurrentPlayer() {
    return this.players.get(this.currentPlayerIndex);
  }

  @Override
  public void update(Observable o, Object uin) {
    UserInterfaceNotification notification = (UserInterfaceNotification) uin;
    UserInterfaceEvents event = notification.event;
    Map<String, Object> args = notification.args;

    if (event == UserInterfaceEvents.ROLL_DICES) {
      if (this.validateRollDices()) {
        this.rollDices();
      } else {
        this.ui.showMessage("Ação proibida!");
      }

    } else if (event == UserInterfaceEvents.PASS_TURN) {
      if (this.validatePassTurn()) {
        this.passTurn();
      } else {
        this.ui.showMessage("Ação proibida!");
      }
      
    } else if (event == UserInterfaceEvents.VIEW_SQUARE) {
      this.viewSquare();

    } else if (event == UserInterfaceEvents.BUY_PROPERTY) {
      this.buyProperty();

    } else if (event == UserInterfaceEvents.JAIL_PASS) {
      if (this.validateUseJailPass()) {
        this.useJailPass();
      } else {
        this.ui.showMessage("Ação proibida!");
      }

    } else if (event == UserInterfaceEvents.GO_BANKRUPT) {
      if (this.validateGoBankrupt()) {
        this.goBankrupt();
      } else {
        this.ui.showMessage("Ação proibida!");
      }
    } else if (event == UserInterfaceEvents.CARD_VIEW) {
      NegotiableCard card = (NegotiableCard) args.get("card");
      this.viewCard(card);

    } else if (event == UserInterfaceEvents.CARD_NEGOTIATE) {
      NegotiableCard card = (NegotiableCard) args.get("card");
      this.negotiateCard(card);

    } else if (event == UserInterfaceEvents.CARD_MORTGAGE) {
      PropertyCard card = (PropertyCard) args.get("card");
      if (this.validateDoMortgage(card)) {
        this.doMortgage(card);
      } else {
        this.ui.showMessage("Ação proibida!");
      }

    } else if (event == UserInterfaceEvents.CARD_NEW_BUILDING) {
      TerrainCard card = (TerrainCard) args.get("card");
      if (this.validateNewBuilding(card)) {
        this.newBuilding(card);
      } else {
        this.ui.showMessage("Ação proibida!");
      }

    } else if (event == UserInterfaceEvents.CARD_REMOVE_BULDING) {
      TerrainCard card = (TerrainCard) args.get("card");
      if (this.validateRemoveBuilding(card)) {
        this.removeBuilding(card);
      } else {
        this.ui.showMessage("Ação proibida!");
      }
    }

    this.ui.repaint();
  }

  private boolean validateRollDices() {
    if (this.hasCurrPlayerPlayed) {
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
    if (currPlayer.canGoBankrupt()) {
      return false;
    } else if (this.hasCurrPlayerPlayed) {
      return !currPlayer.goesAgain();
    } else {
      return false;
    }
  }

  private void passTurn() {
    this.getCurrentPlayer().resetDoubleCounter();
    this.hasCurrPlayerPlayed = false;

    this.currentPlayerIndex += 1;
    this.currentPlayerIndex %= this.players.size();
    while (this.getCurrentPlayer().isOutOfGame()) {
      this.currentPlayerIndex += 1;
      this.currentPlayerIndex %= this.players.size();
    }
  }
  
  private void viewSquare() {
    String description = this.getCurrentPlayer().getSquare().getDescription();
    this.ui.showMessage(description);
  }
  
  private void buyProperty(){
    Player currPlayer = this.getCurrentPlayer();
    Square square = currPlayer.getSquare();
    
    if(!hasCurrPlayerPlayed) {
      this.ui.showMessage("Você deve jogar os dados antes!");
      return; 
    }
    
    if(!(square instanceof PropertySquare)) {
      this.ui.showMessage("Não pode comprar: não é uma propriedade!");
      return;
    }

    PropertySquare psquare = (PropertySquare)square;
    PropertyCard card = psquare.getAssociatedCard(); 
    Entity owner = card.getOwner();
    
    if(owner == currPlayer) {
      this.ui.showMessage("Você já é o dono!");
      return;
    }
    
    if(owner != this.getBank()){
      this.negotiateOthersCard(card, currPlayer);
      return;
    }
    
    //owner == bank
    
    if(!currPlayer.affords(card.getPrice())) {
      ui.showMessage("Desculpe, " + currPlayer.getName() + ", mas você não tem dinheiro suficiente para tentar comprar este terreno.");
      return;
    }
    
    String message = currPlayer.getName() + ", deseja adquirir esta carta por $" + card.getPrice() + "?\n\n" + card.getInfoText();
    if(ui.askBoolean(message)) {
      currPlayer.buyProperty(card);
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

  private void viewCard(NegotiableCard card) {
    this.ui.showMessage(card.getInfoText(), card.getName());
  }

  private void negotiateCard(NegotiableCard card) {
    Player currPlayer = this.getCurrentPlayer();

    if (card instanceof TerrainCard && !((TerrainCard) card).isEmpty()) {
      this.ui
          .showMessage("Há construções no terreno, devem ser demolidos pelo dono antes de negociar!");
      return;
    }

    if (currPlayer.owns(card)) {
      this.negotiateOwnCard(card, currPlayer);
    } else {
      this.negotiateOthersCard(card, currPlayer);
    }
  }

  private void negotiateOwnCard(NegotiableCard card, Player currPlayer) {
    List<Object> others = new ArrayList<Object>();
    if (card instanceof PropertyCard && !((PropertyCard) card).isInMortgage()) {
      others.add(this.bank);
      if (this.getRemainingPlayers().size() > 2) {
        others.add("Leilão");
      }
    }
    for (Player p : this.players) {
      if (p != currPlayer && !p.isOutOfGame()) {
        others.add(p);
      }
    }
    Object[] options = others.toArray();

    Object choice = this.ui.askOptions(currPlayer
        + ", com quem você quer negociar?", options);
    if (choice == bank) {
      this.negotiateOwnCardWithBank((PropertyCard) card, currPlayer);
    } else if ("Leilão".equals(choice)) {
      this.negotiateOwnCardWithAuction((PropertyCard) card, currPlayer);
    } else {
      this.negotiateOwnCardWithAnotherPlayer(card, currPlayer, (Player) choice);
    }
  }

  private void negotiateOwnCardWithBank(PropertyCard card, Player owner) {
    int amount = card.getBankOffer();
    boolean answer = this.ui.askBoolean("O banco comprará esta carta por $"
        + amount + ". Aceita?");
    if (answer) {
      owner.sellCardTo(bank, card, amount);
    }
  }

  private void negotiateOwnCardWithAuction(PropertyCard card, Player owner) {
    PropertyCard pcard = (PropertyCard) card;
    Player highestBidder = null;
    int highestBid = pcard.getBankOffer();
    int i = 0;

    while (true) {
      Player bidder = this.players.get(i);

      if (bidder == highestBidder) {
        break;
      }

      if (bidder != owner && bidder.getBalance() > highestBid) {
        int bid = this.ui.askInt(bidder + ", deseja fazer uma oferta?", highestBid + 1, bidder.getBalance());
        if (bid > 0) {
          highestBid = bid;
          highestBidder = bidder;
        }
      }

      i += 1;
      i %= this.players.size();

      if (i == 0 && highestBidder == null) {
        break;
      }
    }

    if (highestBidder == null) {
      this.ui.showMessage("Não há interessados em comprar a propriedade.");
    } else {
      this.ui.showMessage("Vendido para " + highestBidder.getName() + " por $" + highestBid + "!");
      owner.sellCardTo(highestBidder, pcard, highestBid);
    }
  }

  private void negotiateOwnCardWithAnotherPlayer(NegotiableCard card, Player owner, Player other) {
    int amount;
    boolean answer = false;

    if (card instanceof PropertyCard && ((PropertyCard) card).isInMortgage()) {
      int mortgageAmount = ((PropertyCard) card).getRemoveFromMortgageValue();
      amount = this.ui.askInt(owner + ", quanto você pede?", other.getBalance() - mortgageAmount);
      answer = this.ui.askBoolean(other + ", " + owner + " deseja te vendar a carta por $" + amount + ". Você também terá que pagar $" + mortgageAmount + " ao banco. Aceita?");
      if (answer) {
        other.charge(mortgageAmount);
      }
    } else {
      amount = this.ui.askInt(owner + ", quanto você pede?", other.getBalance());
      answer = this.ui.askBoolean(other + ", " + owner + " deseja te vendar a carta por $" + amount + ". Aceita?");
    }
    if (answer) {
      owner.sellCardTo(other, card, amount);
    }
  }

  private void negotiateOthersCard(NegotiableCard card, Player currPlayer) {
    Player other = (Player) card.getOwner();
    int amount = this.ui.askInt(currPlayer + ", quanto você oferece a " + other + "?", currPlayer.getBalance());
    if (amount == -1) {
      return;
    }
    boolean answer = this.ui.askBoolean(other + ", " + currPlayer + " deseja comprar a carta por $" + amount + ". Aceita?");
    if (answer) {
      other.sellCardTo(currPlayer, card, amount);
    }
  }

  private boolean validateDoMortgage(PropertyCard card) {
    Player currPlayer = this.getCurrentPlayer();
    return currPlayer.owns(card);
  }

  private void doMortgage(PropertyCard card) {
    card.toggleMortgage();
  }

  private boolean validateNewBuilding(TerrainCard card) {
    Player currPlayer = this.getCurrentPlayer();
    List<TerrainCard> allCards = this.terrainDeck.findByColor(card.getColor());
    if (!currPlayer.owns(card)) {
      this.ui.showMessage("Você nao é o dono desta carta!");
      return false;
    }
    if (!currPlayer.owns(allCards)) {
      this.ui.showMessage("Você deve possuir todas as cartas dessa cor antes de constuir.");
      return false;
    }
    if (card.isFull()) {
      this.ui.showMessage("Não é possível construir mais construções neste terreno.");
      return false;
    }
    if (card.isInMortgage()) {
      this.ui.showMessage("É necessário retirar da hipoteca antes de construir casas.");
      return false;
    }
    for (TerrainCard c : allCards) {
      if (c.getBuildings() < card.getBuildings()) {
        this.ui.showMessage("Você deve antes construir mais construções nos outros terrenos de mesma cor.");
        return false;
      }
    }
    int cost = card.getNextBuildingCost();
    if (!currPlayer.affords(cost)) {
      this.ui.showMessage("Dinheiro insuficiente, eh necessario $" + cost);
      return false;
    }
    return true;
  }

  private void newBuilding(TerrainCard card) {
    Player currPlayer = this.getCurrentPlayer();
    int cost = card.getNextBuildingCost();
    if (currPlayer.affords(cost) && card.addBuilding()) {
      currPlayer.charge(cost);
      this.ui.showMessage(currPlayer.getName() + " gastou $" + cost + " em uma nova construcao em " + card.getName());
    }
  }

  private boolean validateRemoveBuilding(TerrainCard card) {
    Player currPlayer = this.getCurrentPlayer();
    List<TerrainCard> allCards = this.terrainDeck.findByColor(card.getColor());
    if (!currPlayer.owns(card)) {
      this.ui.showMessage("Voce nao eh o dono desta carta!");
      return false;
    }
    if (card.isEmpty()) {
      this.ui.showMessage("Não há construções neste terreno.");
      return false;
    }
    for (TerrainCard c : allCards) {
      if (c.getBuildings() > card.getBuildings()) {
        this.ui.showMessage("Você deve antes demolir construções em outros terrenos desta mesma cor.");
        return false;
      }
    }
    return true;
  }

  private void removeBuilding(TerrainCard card) {
    Player currPlayer = this.getCurrentPlayer();
    int earnings = card.getNextBuildingRemovalEarnings();
    if (card.removeBuilding()) {
      currPlayer.give(earnings);
      this.ui.showMessage(currPlayer.getName() + " ganhou $" + earnings + " demolindo uma construcao em " + card.getName());
    }
  }

  private void checkWinner() {
    List<Player> rem = this.getRemainingPlayers();
    if (rem.size() == 1) {
      this.ui.showMessage("O GANHADOR EH " + rem.get(0), "FIM DE JOGO");
      System.exit(0);
    }
  }

}

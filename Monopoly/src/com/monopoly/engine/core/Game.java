package com.monopoly.engine.core;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.monopoly.engine.cards.Card;
import com.monopoly.engine.cards.CompanyDeck;
import com.monopoly.engine.cards.TerrainDeck;
import com.monopoly.engine.cards.chancecards.ChanceDeck;
import com.monopoly.engine.squares.Square;
import com.monopoly.ui.UserInterface;
import com.monopoly.ui.UserInterfaceEvents;

public class Game implements Observer {
  
  private Board board;
  private ChanceDeck chanceDeck;
  private CompanyDeck companyDeck;
  private TerrainDeck terrainDeck;
  private List<Player> players;
  private int currentPlayerIndex;
  private DoubleDice doubleDice = new DoubleDice();
  private UserInterface ui;
  
  private void initializeChanceDeck() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/chance.json"));
    
    this.chanceDeck = new ChanceDeck(arr);
    System.out.println("Initialized Chance Deck succesfully!");
  }
  
  private void initializeCompanyDeck() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/company.json"));
    
    this.companyDeck = new CompanyDeck(arr);
    System.out.println("Initialized Company Deck succesfully!");
  }
  
  private void initializeTerrainDeck() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/terrain.json"));
    
    this.terrainDeck = new TerrainDeck(arr);
    System.out.println("Initialized Terrain Deck succesfully!");
  }

  private void initializeBoard() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/board.json"));

    this.board = new Board(arr, this.terrainDeck, this.companyDeck);
    System.out.println("Initialized Board succesfully!");
  }

  private void initializePlayers(String[] playerNames) throws IllegalArgumentException {
    if(playerNames.length < 2 || playerNames.length > 6){
      throw new IllegalArgumentException("Number of players must be between 2 and 6 (inclusive).");
    }

    this.players = new ArrayList<Player>();

    for(int i = 0; i < playerNames.length; i++){
      String name = playerNames[i];
      PlayerColor color = PlayerColor.values()[i];
      this.players.add(new Player(name, color));
    }
    this.currentPlayerIndex = 0;

    System.out.println("Initialized Players succesfully!");
  }

  public Game(){
    String[] hardcodedPlayers = new String[]{"Felipe", "Guilherme", "Ivan"};

    try {
      this.initializeChanceDeck();
      this.initializeCompanyDeck();
      this.initializeTerrainDeck();
      this.initializeBoard();
      this.initializePlayers(hardcodedPlayers);
    } catch (IOException | ParseException | IllegalArgumentException e) {
      e.printStackTrace();
      System.exit(1);
    }

    System.out.println("Initialized everything succesfully!");
  }

  public void addUI(UserInterface ui) {
    this.ui = ui;
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

  public int getCurrentPlayerIndex() {
    return this.currentPlayerIndex;
  }

  public DoubleDice getDices() {
    return this.doubleDice;
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

  public void nextTurn() {
    if(!doubleDice.wasLastRollDouble()) {
      this.currentPlayerIndex += 1;
      this.currentPlayerIndex %= this.players.size();
    
      doubleDice = new DoubleDice();
    }
  }

  @Override
  public void update(Observable o, Object arg) {
    if(arg == UserInterfaceEvents.GUI_ROLL_DICES){
      this.rollDices();
    }
  }

  private void rollDices() {
    ui.diceWasRolled(this.getDices().roll());
    this.movePin();
    this.nextTurn();
  }

  public void movePin() {
    //TODO check for 3 doubles and go to jail

    int steps = doubleDice.getLastRollTotal();

    Player currPlayer = this.getCurrentPlayer();
    Square currSquare = this.board.getSquare(currPlayer.getPosition());

    for(int i = 0; i < steps; i++){
      currPlayer.step();
      currSquare = this.board.getSquare(currPlayer.getPosition());
      currSquare.affectPassingPlayer(this, currPlayer, ui);
      this.ui.repaint();
      // TODO timer com repaint. Thread.sleep não funciona.
    }

    currSquare.affectLandingPlayer(this, currPlayer, ui);
  }
}

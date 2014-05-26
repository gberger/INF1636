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
import com.monopoly.ui.UserInterface;
import com.monopoly.ui.UserInterfaceEvents;

public class Game implements Observer {
  
  private Board board;
  private ChanceDeck chanceDeck;
  private CompanyDeck companyDeck;
  private TerrainDeck terrainDeck;
  private List<Player> players;
  private int currentPlayerIndex;
  private UserInterface ui;
  private boolean hasCurrPlayerPlayed = false;
  
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

  private void initializePlayers(String[] playerNames) throws IllegalArgumentException {
    if(playerNames.length < 2 || playerNames.length > 6){
      throw new IllegalArgumentException("Number of players must be between 2 and 6 (inclusive).");
    }

    this.players = new ArrayList<Player>();

    for(int i = 0; i < playerNames.length; i++){
      String name = playerNames[i];
      PlayerColor color = PlayerColor.values()[i];
      this.players.add(new Player(name, color, this));
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

  public void setUI(UserInterface ui) {
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
  public void update(Observable o, Object arg) {    
    if(arg == UserInterfaceEvents.ROLL_DICES) {
      if(this.validateRollDices()) {
        this.rollDices(); 
      } else {
        this.ui.showMessage("Ação proibida!");
      }
      
    } else if(arg == UserInterfaceEvents.PASS_TURN) {
      if(this.validatePassTurn()) {
        this.passTurn(); 
      } else {
        this.ui.showMessage("Ação proibida!");
      }
      
    } else if(arg == UserInterfaceEvents.JAIL_PASS) {
      if(this.validateUseJailPass()) {
        this.useJailPass(); 
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
    if(this.hasCurrPlayerPlayed) {
      Player currPlayer = this.getCurrentPlayer();
      return !currPlayer.goesAgain();
    } else {
      return false;
    }
  }

  public void passTurn() {
    Player currPlayer = this.getCurrentPlayer();
    currPlayer.resetDoubleCounter();
    this.currentPlayerIndex += 1;
    this.currentPlayerIndex %= this.players.size();
    this.hasCurrPlayerPlayed = false;
  }

  private boolean validateUseJailPass() {
    Player currPlayer = this.getCurrentPlayer();
    return currPlayer.isInJail() && currPlayer.hasJailPass();
  }

  private void useJailPass() {
    Player currPlayer = this.getCurrentPlayer();
    currPlayer.useJailPass();
  }
}

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Game {
  
  private Board board;
  private ChanceDeck chanceDeck;
  private CompanyDeck companyDeck;
  private TerrainDeck terrainDeck;
  private List<Player> players;
    
  private void initializeBoard() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/board.json"));
    
    this.board = new Board(arr);
  }
  
  private void initializeChanceDeck() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/chance.json"));
    
    this.chanceDeck = new ChanceDeck(arr);
  }
  
  private void initializeCompanyDeck() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/company.json"));
    
    this.companyDeck = new CompanyDeck(arr);
  }
  
  private void initializeTerrainDeck() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/terrain.json"));
    
    this.terrainDeck = new TerrainDeck(arr);
  }
  /*
   * Normally, you'd pass a List<Player> to the constructor.
   * This calls the constructor with a hardcoded player list. 
   */
  public Game() {
    this(getHardcodedPlayers());
  }
  
  private static List<Player> getHardcodedPlayers() {
    List<Player> players = new ArrayList<Player>();
    players.add(new Player("Felipe", PlayerColor.BLUE));
    players.add(new Player("Guilherme", PlayerColor.RED));
    return players;
  }
  

  public Game(List<Player> players){
    try {
      this.initializeBoard();
      this.initializeChanceDeck();
      this.initializeCompanyDeck();
      this.initializeTerrainDeck();
    } catch (IOException | ParseException e) {
      e.printStackTrace();
      System.exit(1);
    } 
    
    this.players = players;
    
    System.out.println("Initialized succesfully!");
    System.out.println(this.chanceDeck.get(0).getText());
    System.out.println(this.players.get(0).getName());
  }
  
  public List<Player> getPlayers(){
    return this.players;
  }
  
}

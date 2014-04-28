import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Game {
  
  private Board board;
  private ChanceDeck chanceDeck;
  private CompanyDeck companyDeck;
  private TerrainDeck terrainDeck;
  
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
  
  public Game() {
    try {
      this.initializeBoard();
      this.initializeChanceDeck();
      this.initializeCompanyDeck();
      this.initializeTerrainDeck();
    } catch (IOException | ParseException e) {
      e.printStackTrace();
      System.exit(1);
    } 
    System.out.println("Initialized succesfully!");
    System.out.print(this.chanceDeck.get(0).getText());
  }
  
}

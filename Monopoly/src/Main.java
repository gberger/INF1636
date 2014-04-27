import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {
  
  private static Board board;
  private static ChanceDeck chanceDeck;
  private static CompanyDeck companyDeck;
  private static TerrainDeck terrainDeck;
  
  private static void initializeBoard() throws FileNotFoundException, IOException, ParseException{
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/board.json"));
    
    board = new Board(arr);
  }
  
  private static void initializeChanceDeck() throws FileNotFoundException, IOException, ParseException{
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/chance.json"));
    
    chanceDeck = new ChanceDeck(arr);
  }
  
  private static void initializeCompanyDeck() throws FileNotFoundException, IOException, ParseException{
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/company.json"));
    
    companyDeck = new CompanyDeck(arr);
  }
  
  private static void initializeTerrainDeck() throws FileNotFoundException, IOException, ParseException{
    JSONParser parser = new JSONParser();
    JSONArray arr = (JSONArray) parser.parse(new FileReader("data/terrain.json"));
    
    terrainDeck = new TerrainDeck(arr);
  }

  public static void main(String[] args) {
    try {
      initializeBoard();
      initializeChanceDeck();
      initializeCompanyDeck();
      initializeTerrainDeck();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    } 
    System.out.println("Initialized succesfully!");
    
    System.out.print(chanceDeck.get(0).getText());
    
  }
  
}

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Board {
  private List<Square> squares;

  public Board(JSONArray arr) {
    this.squares = new ArrayList<Square>();

    for(Object obj : arr){
      JSONObject jobj = (JSONObject) obj;
      Square square = new Square(jobj);
      squares.add(square);
    }
  }
}
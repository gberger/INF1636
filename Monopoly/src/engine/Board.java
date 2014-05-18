package engine;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Board {
  private List<Square> squares;

  public Board(JSONArray arr, TerrainDeck terrains, CompanyDeck companies) {
    this.squares = new ArrayList<Square>();

    for(Object obj : arr){
      JSONObject jobj = (JSONObject) obj;

      String type = (String)jobj.get("type");
      Square square = Square.fromType(type, jobj, companies, terrains);

      squares.add(square.getId(),square);
    }
  }
  
  public Square getSquare(int id) {
    return this.squares.get(id);
  }
  
  public Square getJail() {
    for(Square sq : this.squares) {
      if(sq instanceof JailSquare){
        return sq;
      }
    }
    return null;
  }

}
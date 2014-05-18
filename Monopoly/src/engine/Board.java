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
      Square square = new Square(jobj, terrains, companies);
      squares.add(square.getId(),square);
    }
  }
  
  public Square getSquare(int id) {
    return this.squares.get(id);
  }
  
  public Square getJail() {
    for(Square sq : this.squares) {
      if(sq.getType() == SquareType.JAIL){
        return sq;
      }
    }
    return null;
  }

}
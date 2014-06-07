package com.monopoly.engine.core;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.monopoly.engine.squares.JailSquare;
import com.monopoly.engine.squares.Square;
import com.monopoly.engine.squares.StartSquare;

public class Board {
  private List<Square> squares;
  @SuppressWarnings("unused")
  private Game game;
  
  public Board(JSONArray arr, Game game) {
    this.game = game;
    this.squares = new ArrayList<Square>();

    for(Object obj : arr){
      JSONObject jobj = (JSONObject) obj;

      String type = (String)jobj.get("type");
      Square square = Square.fromType(type, jobj, game);

      squares.add(square.getId(),square);
    }
  }
  
  public Square getSquare(int id) {
    return this.squares.get(id);
  }
  
  public List<Square> getSquares() {
    return this.squares;
  }

  public Square getJail() {
    for(Square sq : this.squares) {
      if(sq instanceof JailSquare){
        return sq;
      }
    }
    return null;
  }

  public Square getStart() {
    for(Square sq : this.squares) {
      if(sq instanceof StartSquare){
        return sq;
      }
    }
    return null;
  }

}
package com.monopoly.engine.cards;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.squares.TerrainColor;


public class TerrainDeck extends Deck<TerrainCard> {

  public TerrainDeck(JSONArray arr, Game game) {
    this.cards = new ArrayList<TerrainCard>();

    for(Object obj : arr){
      JSONObject jobj = (JSONObject) obj;
      TerrainCard card = new TerrainCard(jobj, game);
      cards.add(card);
    }
  }

  public TerrainCard findByName(String name) {
    for(TerrainCard card : this.cards) {
      if(name.equals(card.getName()))
        return card;
    }
    return (TerrainCard)null;
  }
  
  public List<TerrainCard> findByColor(TerrainColor color){
    List<TerrainCard> res = new ArrayList<TerrainCard>();
    
    for(TerrainCard c : this.cards){
      if(c.getColor() == color){
        res.add(c);
      }
    }
    
    return res;
  }
}

package com.monopoly.engine.cards;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;


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

}

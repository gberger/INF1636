package com.monopoly.engine.cards.chancecards;

import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.monopoly.engine.cards.Deck;
import com.monopoly.engine.core.Game;

public class ChanceDeck extends Deck<ChanceCard> {
  
  public ChanceDeck(JSONArray arr, Game game) {
    this.cards = new ArrayList<ChanceCard>();
    
    for(Object obj : arr){
      JSONObject jobj = (JSONObject) obj;
      ChanceCard card = ChanceCard.fromType((String)jobj.get("type"), jobj, game);
      cards.add(card);
    }

    Collections.shuffle(cards);
  }

  public ChanceCard draw(){
    return this.cards.remove(this.size() - 1);
  }

  public void addToBottom(ChanceCard card){
    this.cards.add(0, card);
  }

}

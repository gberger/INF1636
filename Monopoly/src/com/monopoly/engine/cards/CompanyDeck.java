package com.monopoly.engine.cards;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;


public class CompanyDeck extends Deck<CompanyCard> {

  public CompanyDeck(JSONArray arr, Game game) {
    this.cards = new ArrayList<CompanyCard>();
    
    for(Object obj : arr){
      JSONObject jobj = (JSONObject) obj;
      CompanyCard card = new CompanyCard(jobj, game);
      cards.add(card);
    }
  }
  
  public CompanyCard findByName(String name) {
    for(CompanyCard card : this.cards) {
      if(name.equals(card.getName()))
        return card;
    }
    return (CompanyCard)null;
  }

}

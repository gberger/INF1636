package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;

public class CompanySquare extends PropertySquare {
  public CompanySquare(JSONObject jobj, Game game) {
    this.game = game;
    this.id = new Long((long) jobj.get("id")).intValue();
    this.associatedCard = game.getCompanyDeck().findByName((String)jobj.get("name"));
  }
}

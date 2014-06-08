package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;

public class TerrainSquare extends PropertySquare {
  public TerrainSquare(JSONObject jobj, Game game) {
    this.game = game;
    this.id = new Long((long) jobj.get("id")).intValue();
    this.associatedCard = game.getTerrainDeck().findByName((String)jobj.get("name"));
  }

  @Override
  public String getDescription() {
    return "Terreno \n\n" + this.associatedCard.getInfoText();
  }

}

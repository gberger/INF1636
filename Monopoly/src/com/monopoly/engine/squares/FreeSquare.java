package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;

public class FreeSquare extends Square {
  public FreeSquare(JSONObject jobj, Game game) {
    this.game = game;
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Player player) {
    // do nothing
  }

  @Override
  public void affectPassingPlayer(Player player) {
    // do nothing
  }

  @Override
  public String getDescription() {
    return "Parada livre";
  }
}

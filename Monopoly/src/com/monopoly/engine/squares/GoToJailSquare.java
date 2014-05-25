package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;

public class GoToJailSquare extends Square {
  public GoToJailSquare(JSONObject jobj, Game game) {
    this.game = game;
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Player player) {
    player.goToJail();
  }

  @Override
  public void affectPassingPlayer(Player player) {
    // do nothing
  }

}

package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;
import com.monopoly.ui.UserInterface;

public class StartSquare extends Square {
  public StartSquare(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Game game, Player player, UserInterface ui) {
    // do nothing
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, UserInterface ui) {
    player.give(200);
    ui.showMessage(player.getName() + " passou pelo início e recebeu $200!");
  }
}

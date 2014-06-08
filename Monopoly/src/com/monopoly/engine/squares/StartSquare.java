package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;
import com.monopoly.ui.UserInterface;

public class StartSquare extends Square {
  public StartSquare(JSONObject jobj, Game game) {
    this.game = game;
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Player player) {
    // do nothing
  }

  @Override
  public void affectPassingPlayer(Player player) {
    UserInterface ui = this.game.getUI();
    player.give(200);
    ui.showMessage(player.getName() + " passou pelo início e recebeu $200!");
  }

  @Override
  public String getDescription() {
    return "Início: ganhe $200 ao passar.";
  }
}

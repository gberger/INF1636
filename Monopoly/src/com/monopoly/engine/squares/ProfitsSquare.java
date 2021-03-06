package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;
import com.monopoly.ui.UserInterface;

public class ProfitsSquare extends Square {
  public ProfitsSquare(JSONObject jobj, Game game) {
    this.game = game;
    this.id = new Long((long) jobj.get("id")).intValue();
  }
  
  @Override
  public void affectLandingPlayer(Player player) {
    UserInterface ui = this.game.getUI();
    player.give(200);
    ui.showMessage(player.getName() + " ganhou $200 de lucros e dividendos!");
  }

  @Override
  public void affectPassingPlayer(Player player) {
    // do nothing
  }

  @Override
  public String getDescription() {
    return "Lucros e Dividendos: ganhe $200 ao parar";
  }
}

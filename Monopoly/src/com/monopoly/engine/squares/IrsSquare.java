package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;
import com.monopoly.ui.UserInterface;

public class IrsSquare extends Square {
  public IrsSquare(JSONObject jobj, Game game) {
    this.game = game;
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Player player) {
    UserInterface ui = this.game.getUI();
    player.payTo(this.game.getBank(), 200);
    ui.showMessage(player.getName() + " deve pagar $200 de imposto de renda.");
  }

  @Override
  public void affectPassingPlayer(Player player) {
    // do nothing
  }
}

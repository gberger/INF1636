package engine;

import org.json.simple.JSONObject;

import ui.UI;

public class StartSquare extends Square {
  public StartSquare(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Game game, Player player, UI ui) {
    // do nothing
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, UI ui) {
    player.give(200);
    ui.showMessage(player.getName() + " passou pelo início e recebeu $200!");
  }
}

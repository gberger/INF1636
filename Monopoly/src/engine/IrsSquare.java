package engine;

import org.json.simple.JSONObject;

import ui.UI;

public class IrsSquare extends Square {
  public IrsSquare(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Game game, Player player, UI ui) {
    player.charge(200);
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, UI ui) {
    // do nothing
  }
}
package engine;

import org.json.simple.JSONObject;

import ui.UI;

public class FreeSquare extends Square {
  public FreeSquare(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Game game, Player player, UI ui) {
    // do nothing
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, UI ui) {
    // do nothing
  }
}

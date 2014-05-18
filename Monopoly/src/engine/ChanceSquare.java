package engine;

import org.json.simple.JSONObject;

import ui.UI;

public class ChanceSquare extends Square {
  public ChanceSquare(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Game game, Player player, UI ui) {
    // TODO
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, UI ui) {
    // do nothing
  }

}

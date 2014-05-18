package engine;

import org.json.simple.JSONObject;

import ui.UI;

public class ChanceSquare extends Square {
  public ChanceSquare(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  public void affectLandingPlayer(Game game, Player player, UI ui) {
    // TODO
  }

}

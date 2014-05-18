package engine;

import org.json.simple.JSONObject;

import ui.UI;

public class ProfitsSquare extends Square {
  public ProfitsSquare(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
  }
  
  @Override
  public void affectLandingPlayer(Game game, Player player, UI ui) {
    player.give(200);
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, UI ui) {
    // do nothing
  }
}

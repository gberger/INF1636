package engine;

import org.json.simple.JSONObject;

import ui.GUI;

public class IrsSquare extends Square {
  public IrsSquare(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Game game, Player player, GUI ui) {
    player.charge(200);
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, GUI ui) {
    // do nothing
  }
}

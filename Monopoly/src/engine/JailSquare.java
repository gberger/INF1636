package engine;

import org.json.simple.JSONObject;

import ui.UserInterface;

public class JailSquare extends Square {
  public JailSquare(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Game game, Player player, UserInterface ui) {
    // do nothing (visitor)
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, UserInterface ui) {
    // do nothing
  }
}

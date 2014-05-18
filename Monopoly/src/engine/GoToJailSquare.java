package engine;

import org.json.simple.JSONObject;

import ui.UI;

public class GoToJailSquare extends Square {
  public GoToJailSquare(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Game game, Player player, UI ui) {
    player.goToJail(game.getBoard());
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, UI ui) {
    // do nothing
  }

}

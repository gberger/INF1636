package engine;

import org.json.simple.JSONObject;

import ui.UserInterface;

public class GoToStartChanceCard extends ChanceCard {

  public GoToStartChanceCard(JSONObject jobj) {
    this.title = (String) jobj.get("title");
    this.text = (String) jobj.get("text");
    this.amount = new Long((long)jobj.get("amount")).intValue();
  }
  
  @Override
  public void affectPlayer(Game game, Player player, UserInterface ui) {
    player.goToStart(game.getBoard());
  }

  @Override
  public boolean isReaddedToDeckAfterReading(){
    return true;
  }

}

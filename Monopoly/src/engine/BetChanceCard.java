package engine;

import org.json.simple.JSONObject;

import ui.UI;

public class BetChanceCard extends ChanceCard {

  public BetChanceCard(JSONObject jobj) {
    this.title = (String) jobj.get("title");
    this.text = (String) jobj.get("text");
    this.amount = new Long((long)jobj.get("amount")).intValue();
  }
  
  @Override
  public void affectPlayer(Game game, Player player, UI ui) {
    player.give(this.amount);
    for(Player p : game.getPlayers()) {
      if(player != p){
        p.charge(this.amount);
      }
    }
  }

  @Override
  public boolean isReaddedToDeckAfterReading(){
    return true;
  }

}

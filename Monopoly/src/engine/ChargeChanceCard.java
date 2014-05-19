package engine;

import org.json.simple.JSONObject;

import ui.GUI;

public class ChargeChanceCard extends ChanceCard {

  public ChargeChanceCard(JSONObject jobj) {
    this.title = (String) jobj.get("title");
    this.text = (String) jobj.get("text");
    this.amount = new Long((long)jobj.get("amount")).intValue();
  }
  
  @Override
  public void affectPlayer(Game game, Player player, GUI ui) {
    player.charge(this.amount);
  }

  @Override
  public boolean isReaddedToDeckAfterReading(){
    return true;
  }

}

package engine;

import org.json.simple.JSONObject;

import ui.UI;

public class ChanceCard extends Card {
  private String title;
  private String text;
  private String type;
  private int amount;

  public ChanceCard(JSONObject jobj) {
    this.title = (String) jobj.get("title");
    this.text = (String) jobj.get("text");
    this.type = (String) jobj.get("type");
    this.amount = new Long((long)jobj.get("amount")).intValue();
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }

  public String getType() {
    return type;
  }

  public int getAmount() {
    return amount;
  }

  // Returns whether the card should be re-added to the deck or not
  public boolean affectPlayer(Game game, Player player, UI ui) {
    // TODO separate into sub-classes by type
    // TODO ui.showMessage

    if("receive".equals(this.type)) {
      player.give(this.amount);
      return true;
    }

    if("charge".equals(this.type)) {
      player.charge(this.amount);
      return true;
    }

    if("bet".equals(this.type)) {
      player.give(this.amount);
      for(Player p : game.getPlayers()) {
        if(player != p){
          p.charge(this.amount);  
        }
      }
      return true;
    }

    if("goToJail".equals(this.type)) {
      player.goToJail(game.getBoard());
      return true;
    }

    if("goToStart".equals(this.type)) {
      player.goToStart(game.getBoard());
      return true;
    }

    if("jailPass".equals(this.type)) {
      player.giveJailPass(this);
      return false;
    }

    return true;
  }

}
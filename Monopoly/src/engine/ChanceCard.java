package engine;

import org.json.simple.JSONObject;

import ui.UI;

public abstract class ChanceCard extends Card {
  protected String title;
  protected String text;
  protected int amount;
  
  public static ChanceCard fromType(String type, JSONObject jobj) {
    if(type.equals("receive"))        { return new ReceiveChanceCard(jobj); } 
    else if(type.equals("charge"))    { return new ChargeChanceCard(jobj); } 
    else if(type.equals("bet"))       { return new BetChanceCard(jobj); } 
    else if(type.equals("goToJail"))  { return new GoToJailChanceCard(jobj); } 
    else if(type.equals("goToStart")) { return new GoToStartChanceCard(jobj); } 
    else if(type.equals("jailPass"))  { return new JailPassChanceCard(jobj); }
    return null;
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }

  public int getAmount() {
    return amount;
  }

  public abstract void affectPlayer(Game game, Player player, UI ui);
  
  // Returns whether the card should be re-added to the deck or not
  public abstract boolean isReaddedToDeckAfterReading();

}
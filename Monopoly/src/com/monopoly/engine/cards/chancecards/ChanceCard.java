package com.monopoly.engine.cards.chancecards;

import org.json.simple.JSONObject;

import com.monopoly.engine.cards.Card;
import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;

public abstract class ChanceCard extends Card {
  protected String title;
  protected String text;
  protected int amount;
  
  public static ChanceCard fromType(String type, JSONObject jobj, Game game) {
    if(type.equals("receive"))        { return new ReceiveChanceCard(jobj, game); } 
    else if(type.equals("charge"))    { return new ChargeChanceCard(jobj, game); } 
    else if(type.equals("bet"))       { return new BetChanceCard(jobj, game); } 
    else if(type.equals("goToJail"))  { return new GoToJailChanceCard(jobj, game); } 
    else if(type.equals("goToStart")) { return new GoToStartChanceCard(jobj, game); } 
    else if(type.equals("jailPass"))  { return new JailPassChanceCard(jobj, game); }
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

  public abstract void affectPlayer(Player player);
  
  // Returns whether the card should be re-added to the deck or not
  public abstract boolean isReaddedToDeckAfterReading();

}
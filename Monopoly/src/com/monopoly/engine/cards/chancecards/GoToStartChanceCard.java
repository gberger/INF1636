package com.monopoly.engine.cards.chancecards;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;

public class GoToStartChanceCard extends ChanceCard {

  public GoToStartChanceCard(JSONObject jobj, Game game) {
    this.game = game;
    this.title = (String) jobj.get("title");
    this.text = (String) jobj.get("text");
    this.amount = new Long((long)jobj.get("amount")).intValue();
  }
  
  @Override
  public void affectPlayer(Player player) {
    player.goToStart();
  }

  @Override
  public boolean isReaddedToDeckAfterReading(){
    return true;
  }

}

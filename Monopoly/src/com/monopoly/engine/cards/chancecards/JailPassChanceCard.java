package com.monopoly.engine.cards.chancecards;

import org.json.simple.JSONObject;

import com.monopoly.engine.cards.NegotiableCard;
import com.monopoly.engine.core.Entity;
import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;

public class JailPassChanceCard extends ChanceCard implements NegotiableCard {

  private Entity owner;
  
  public JailPassChanceCard(JSONObject jobj, Game game) {
    this.game = game;
    this.title = (String) jobj.get("title");
    this.text = (String) jobj.get("text");
    this.amount = new Long((long)jobj.get("amount")).intValue();
    this.owner = this.game.getBank();
  }
  
  @Override
  public void affectPlayer(Player player) {
    player.give(this);
  }

  @Override
  public boolean isReaddedToDeckAfterReading(){
    return false;
  }

  @Override
  public String getName() {
    return "SAIDA LIVRE DA PRISAO";
  }
  
  public String getInfoText() {
    return this.text;
  }

  @Override
  public Entity getOwner() {
    return this.owner;
  }

  @Override
  public void setOwner(Entity e) {
    if(e == null){
      this.owner = this.game.getBank();
    } else {
      this.owner = e;
    }
  }

}

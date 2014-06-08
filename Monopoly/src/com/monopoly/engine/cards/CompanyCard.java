package com.monopoly.engine.cards;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;

public class CompanyCard extends PropertyCard implements NegotiableCard {
  private int multiplier;

  public CompanyCard(JSONObject jobj, Game game) {
    this.game = game;
    this.owner = this.game.getBank();
    this.name = (String) jobj.get("name");
    this.multiplier = new Long((long) jobj.get("multiplier")).intValue();
    this.mortgage = new Long((long) jobj.get("mortgage")).intValue();
    this.price = this.mortgage*2;
  }

  public int getMultiplier() {
    return multiplier;
  }

  @Override
  public int getRent(Player player) {
    int roll = player.getLastRollTotal();
    return this.multiplier * roll;
  }
  
  public String getInfoText()
  {
    String text = this.getName();
    text += "\nValor a pagar: pontos dos dados multiplicados por $" + multiplier;
    text += "\nHipoteca: $" + mortgage;
    return text;
  }
  
  public boolean canBeMortgaged() {
    return true;
  }
 
}
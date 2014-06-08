package com.monopoly.engine.cards;

import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;

public class CompanyCard extends PropertyCard implements NegotiableCard {
  private int multiplier;

  public CompanyCard(JSONObject jobj, Game game) {
    this.game = game;
    this.name = (String) jobj.get("name");
    this.multiplier = new Long((long) jobj.get("multiplier")).intValue();
    this.mortgage = new Long((long) jobj.get("mortgage")).intValue();
    this.price = this.mortgage*2;
  }

  public int getMultiplier() {
    return multiplier;
  }

  public int getRent(int roll) {
    return this.multiplier * roll;
  }
  
  public String getInfoText()
  {
    String text = "Valor a pagar: pontos dos dados multiplicados por $" + multiplier;
    text += "\nHipoteca: $" + mortgage;
    return text;
  }
  
  public boolean canBeMortgaged() {
    return true;
  }
 
}
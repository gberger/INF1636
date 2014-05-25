package com.monopoly.engine.cards;

import org.json.simple.JSONObject;

public class CompanyCard extends PropertyCard {
  private int multiplier;

  public CompanyCard(JSONObject jobj) {
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
 
}
package com.monopoly.engine.cards;

public abstract class PropertyCard extends Card implements NegotiableCard {
  protected int price;
  protected int mortgage;
  protected String name;
  
  public int getPrice() {
    return this.price;
  }
  
  public String getName() {
    return name;
  }
  
  public int getMortgage() {
    return mortgage;
  }

}

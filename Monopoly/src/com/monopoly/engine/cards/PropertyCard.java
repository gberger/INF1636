package com.monopoly.engine.cards;

public abstract class PropertyCard extends Card implements NegotiableCard {
  protected int price;
  protected int mortgage;
  protected String name;
  protected boolean inMortgage;

  public int getPrice() {
    return this.price;
  }
  
  public int getBankOffer() {
    return this.price/2;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getMortgageValue() {
    return this.mortgage;
  }
  
  public boolean isInMortgage() {
    return this.inMortgage;
  }
  
  public abstract String getInfoText();

}

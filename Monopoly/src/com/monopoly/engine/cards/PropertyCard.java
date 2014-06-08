package com.monopoly.engine.cards;

import com.monopoly.engine.core.Player;

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
  
  public int getRemoveFromMortgageValue() {
    return (int)(this.mortgage * 1.2);
  }
  
  public boolean isInMortgage() {
    return this.inMortgage;
  }
  
  public void setMortgage(boolean b) {
    this.inMortgage = b;
  }
  
  public abstract boolean canBeMortgaged();
  
  public abstract String getInfoText();

  public abstract int getRent(Player player);

}

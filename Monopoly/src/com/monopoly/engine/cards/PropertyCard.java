package com.monopoly.engine.cards;

import com.monopoly.engine.core.Entity;
import com.monopoly.engine.core.Player;

public abstract class PropertyCard extends Card implements NegotiableCard {
  protected int price;
  protected int mortgage;
  protected String name;
  protected boolean inMortgage;
  protected Entity owner;

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

  public Entity getOwner(){
    return this.owner;
  }
  
  public void setOwner(Entity e) {
    if(e == null){
      this.owner = this.game.getBank();
    } else {
      this.owner = e;
    }
  }
  
  public abstract boolean canBeMortgaged();
  
  public abstract String getInfoText();

  public abstract int getRent(Player player);

}

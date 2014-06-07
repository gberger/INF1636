package com.monopoly.engine.core;

import com.monopoly.engine.cards.Card;
import com.monopoly.engine.cards.NegotiableCard;

public class Bank implements Entity {

  @Override
  public String getName() {
    return "Banco";
  }
  
  @Override
  public String toString() {
    return this.getName();
  }

  @Override
  public boolean affords(int price) {
    return true;
  }

  @Override
  public void charge(int x) {
    return;
  }

  @Override
  public void give(int x) {
    return;
  }

  @Override
  public void give(NegotiableCard card) {
    return;
  }

  @Override
  public void payTo(Entity other, int x) {
    other.give(x);
  }

}

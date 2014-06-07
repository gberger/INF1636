package com.monopoly.engine.core;

import com.monopoly.engine.cards.Card;
import com.monopoly.engine.cards.NegotiableCard;

public interface Entity {
  public String getName();
  public boolean affords(int price);
  public void charge(int x);
  public void give(int x);
  public void give(NegotiableCard card);
  public void payTo(Entity other, int x);
  public String toString();
}

package com.monopoly.engine.cards;

import com.monopoly.engine.core.Game;

public abstract class Card {
  protected Game game;
  private int id;

  public int getId(){
    return this.id;
  }
}
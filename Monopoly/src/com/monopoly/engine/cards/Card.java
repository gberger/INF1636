package com.monopoly.engine.cards;

import com.monopoly.engine.core.Player;

public abstract class Card {
  private int id;
  private boolean ownable;
  private boolean owned;
  private Player owner;

  public int getId(){
    return this.id;
  }

  public boolean isOwnable(){
    return this.ownable;
  }

  public boolean isOwned(){
    return this.owned;
  }

  public Player getOwner(){
    return this.owner;
  }
}
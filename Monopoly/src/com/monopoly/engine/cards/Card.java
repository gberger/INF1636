package com.monopoly.engine.cards;

import com.monopoly.engine.core.Entity;
import com.monopoly.engine.core.Game;

public abstract class Card {
  protected Game game;
  private int id;
  private boolean ownable;
  private boolean owned;
  private Entity owner;

  public int getId(){
    return this.id;
  }

  public boolean isOwnable(){
    return this.ownable;
  }

  public boolean isOwned(){
    return this.owned;
  }

  public Entity getOwner(){
    return this.owner;
  }
  
  public void setOwner(Entity p) {
    this.owner = p;
  }
}
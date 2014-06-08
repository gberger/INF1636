package com.monopoly.engine.cards;

import com.monopoly.engine.core.Entity;

public interface NegotiableCard {
  public String getName();
  public String getInfoText();
  public Entity getOwner();
  public void setOwner(Entity e);
}

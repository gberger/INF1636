package com.monopoly.engine.core;

public interface Entity {
  public String getName();
  public boolean affords(int price);
  public void charge(int x);
  public void give(int x);
  public void payTo(Entity other, int x);
  public String toString();
}

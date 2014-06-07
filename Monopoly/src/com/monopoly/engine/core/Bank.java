package com.monopoly.engine.core;

public class Bank implements Entity {

  @Override
  public String getName() {
    return "Banco";
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
  public void payTo(Entity other, int x) {
    other.give(x);
  }

}

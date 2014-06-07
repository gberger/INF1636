package com.monopoly.engine.core;
public class Debt { 
  public final Entity other; 
  public final int amount; 
  public Debt(Entity x, int y) { 
    this.other = x; 
    this.amount = y; 
  } 
} 
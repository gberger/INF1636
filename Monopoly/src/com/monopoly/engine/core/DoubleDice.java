package com.monopoly.engine.core;

class Dice {
  private int max;

  public Dice() {
    this(6);
  }

  private Dice(int max){
    this.max = max;
  }

  public int roll() {
    return (int) (Math.random() * this.max + 1);
  }
}

public class DoubleDice {
  private Dice dice;
  private int doubleCounter;
  private int[] lastRoll;

  public DoubleDice() {
    this.dice = new Dice();
    this.doubleCounter = 0;
  }

  public int[] roll() {
    this.lastRoll = new int[2];

    this.lastRoll[0] = dice.roll();
    this.lastRoll[1] = dice.roll();

    if(this.wasLastRollDouble()) {
      this.doubleCounter += 1;
    }

    return this.lastRoll;
  }

  public int[] getLastRoll() {
    return this.lastRoll;
  }

  public int getLastRollTotal() {
    return this.lastRoll[0] + this.lastRoll[1];
  }

  public boolean wasLastRollDouble() {
    return this.lastRoll[0] == this.lastRoll[1];
  }

  public int getDoubleCounter() {
    return this.doubleCounter;
  }
}
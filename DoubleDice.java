private class Dice {
  private int max;

  public Dice() {
    this(6);
  }

  private Dice(int max){
    this.max = max;
  }

  public int roll() {
    return Math.random() * this.max + 1;
  }
}

public class DoubleDice {
  private Dice dice;
  private int doubleCounter;
  private int[2] lastRoll;

  public DoubleDice() {
    this.dice = new Dice();
    this.doubleCounter = 0;
  }

  public void roll() {
    this.lastRoll[0] = dice.roll();
    this.lastRoll[1] = dice.roll();

    if(this.lastRollWasDouble()) {
      this.doubleCounter += 1;
    }
  }

  public int[2] getLastRoll() {
    return this.lastRoll;
  }

  public int getLastRollTotal() {
    return this.lastRoll[0] + this.lastRoll[1];
  }

  public boolean lastRollWasDouble() {
    return this.lastRoll[0] == this.lastRoll[1];
  }

  public int getDoubleCounter() {
    return this.doubleCounter;
  }
}
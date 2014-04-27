import java.util.ArrayList;
import java.util.List;

public class Player {
  private int position;
  private int money;
  private List<Card> cards;
  private boolean inJail;

  public Player() {
    this.position = 0;
    this.money = (8*1) + (10*5) + (10*10) + (10*50) + (8*100) + (2*500);
    this.cards = new ArrayList<Card>();
    this.inJail = false;
  }

  public void step() {
    this.position += 1;
    this.position %= 40;
  }

  public int getPosition() {
    return this.position;
  }

  public boolean affords(int price) {
    return this.money >= price;
  }

  public boolean owns(Card card) {
    return this.cards.contains(card);
  }

  public int charge(int x){
    this.money -= x;
    return x;
  }

  public void receive(int x){
    this.money += x;
  }

  public int getLastRollTotal() {
    // TODO Auto-generated method stub
    return 0;
  }
}
package engine;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private String name;
  private PlayerColor color;
  private int position;
  private int money;
  private List<Card> cards;
  private boolean inJail;

  public Player(String name, PlayerColor color) {
    this.name = name;
    this.color = color;
    this.position = 0;
    this.money = (8*1) + (10*5) + (10*10) + (10*50) + (8*100) + (2*500);
    this.cards = new ArrayList<Card>();
    this.inJail = false;
  }

  public String getName() {
    return name;
  }

  public PlayerColor getColor() {
    return color;
  }

  public int getPosition() {
    return position;
  }

  public int getMoney() {
    return money;
  }

  public List<Card> getCards() {
    return cards;
  }

  public boolean isInJail() {
    return inJail;
  }

  public void step(){
    this.position += 1;
    this.position %= 40;
  }

  public boolean affords(int price) {
    return this.money >= price;
  }

  public boolean owns(PropertyCard card) {
    return this.cards.contains(card);
  }

  public void charge(int x){
    this.money -= x;
  }

  public void give(int x){
    this.money += x;
  }

  public void buyProperty(PropertyCard card) {
    this.charge(card.getPrice());
    this.cards.add(card);
  }

  public void goToJail(Board board) {
    this.position = board.getJail().getPosition();
  }

  public void goToStart(Board board) {
    this.position = board.getStart().getPosition();
    this.give(200);
  }

  public void payTo(Player owner, int rent) {
    this.charge(rent);
    owner.give(rent);
  }

  public void giveJailPass(ChanceCard jailPass) {
    this.cards.add(jailPass);
  }

  public boolean hasJailPass() {
    for(Card c : this.cards) {
      if(c instanceof JailPassChanceCard) {
        return true;
      }
    }
    return false;
  }

}
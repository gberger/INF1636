package engine;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private String name;
  private PlayerColor color;
  private int position;
  private int money;
  private List<OwnableCard> cards;
  private boolean inJail;
  private int lastRollTotal;

  public Player(String name, PlayerColor color) {
    this.name = name;
    this.color = color;
    this.position = 0;
    this.money = (8*1) + (10*5) + (10*10) + (10*50) + (8*100) + (2*500);
    this.cards = new ArrayList<OwnableCard>();
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

  public List<OwnableCard> getCards() {
    return cards;
  }

  public boolean isInJail() {
    return inJail;
  }

  public void step(int places) {
    this.position += places;
    this.position %= 40;
    this.lastRollTotal = places;
  }

  public boolean affords(int price) {
    return this.money >= price;
  }

  public boolean owns(OwnableCard card) {
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
    return this.lastRollTotal;
  }
  
  public void buyProperty(OwnableCard card) {
    this.charge(card.getPrice());
    this.cards.add(card);
  }
}
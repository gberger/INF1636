package engine;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private String name;
  private PlayerColor color;
  private int position;
  private int money;
  private List<PropertyCard> cards;
  private boolean inJail;
  private int lastRollTotal;

  public Player(String name, PlayerColor color) {
    this.name = name;
    this.color = color;
    this.position = 0;
    this.money = (8*1) + (10*5) + (10*10) + (10*50) + (8*100) + (2*500);
    this.cards = new ArrayList<PropertyCard>();
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

  public List<PropertyCard> getCards() {
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

  public boolean owns(PropertyCard card) {
    return this.cards.contains(card);
  }

  public void charge(int x){
    this.money -= x;
  }

  public void give(int x){
    this.money += x;
  }

  public int getLastRollTotal() {
    return this.lastRollTotal;
  }
  
  public void buyProperty(PropertyCard card) {
    this.charge(card.getPrice());
    this.cards.add(card);
  }

  public void goToJail(Board board) {
    this.position = board.getJail().getPosition();
  }

  public void payTo(Player owner, int rent) {
    this.charge(rent);
    owner.give(rent);
  }
}
package com.monopoly.engine.core;

import java.util.ArrayList;
import java.util.List;

import com.monopoly.engine.cards.Card;
import com.monopoly.engine.cards.PropertyCard;
import com.monopoly.engine.cards.chancecards.ChanceCard;
import com.monopoly.engine.cards.chancecards.JailPassChanceCard;
import com.monopoly.engine.squares.Square;
import com.monopoly.ui.UserInterface;

public class Player {
  private Game game;
  private String name;
  private PlayerColor color;
  private int position;
  private int money;
  private List<Card> cards;
  private boolean inJail;
  private int turnsInJail;
  private DoubleDice doubleDice;

  public Player(String name, PlayerColor color, Game game) {
    this.doubleDice = new DoubleDice();
    this.game = game;
    this.name = name;
    this.color = color;
    this.position = 0;
    this.money = (8*1) + (10*5) + (10*10) + (10*50) + (8*100) + (2*500);
    this.cards = new ArrayList<Card>();
    this.inJail = false;
    this.turnsInJail = 0;
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

  public void goToJail() {
    Board board = this.game.getBoard();
    this.position = board.getJail().getPosition();
    this.inJail = true;
    this.turnsInJail = 0;
  }

  public void goToStart() {
    Board board = this.game.getBoard();
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
  
  public void resetDoubleCounter(){
    this.doubleDice = new DoubleDice();
  }
  
  public void roll(){
    if(this.isInJail()){
      this.rollInJail();
    } else {
      this.rollNotInJail();
    }
  }
  
  private void rollNotInJail() {
    UserInterface ui = this.game.getUI();
    
    int[] values = doubleDice.roll();
    int steps = doubleDice.getLastRollTotal();
    ui.showMessage(this.name + ", seus dados foram " + values[0] + " e " + values[1]);
    
    if(doubleDice.getDoubleCounter() == 3){
      this.goToJail();
    }

    this.move(steps);
  }
  
  private void rollInJail() {
    UserInterface ui = this.game.getUI();
    
    int[] values = doubleDice.roll();
    int steps = doubleDice.getLastRollTotal();
    boolean wasDouble = doubleDice.wasLastRollDouble();
    
    ui.showMessage(this.name + " está na cadeia. Seus dados foram " + values[0] + " e " + values[1]);
    
    if(wasDouble){
      ui.showMessage(this.name + " saiu da cadeia!");
      this.inJail = false;
      this.move(steps);
    } else {
      this.turnsInJail += 1;
      if(turnsInJail == 4){
        ui.showMessage(this.name + ", como se passaram 4 turnos, você pagará a fiança de $50 e sairá da cadeia.");
        this.charge(50);
        this.move(steps);
      } else {
        ui.showMessage(this.name + " continua na cadeia.");
      }
    }
  }
  
  private void move(int steps){
    for(int i = 0; i < steps; i++){
      this.step();
      this.getSquare().affectPassingPlayer(this);
    }

    this.getSquare().affectLandingPlayer(this);
  }
  
  private Square getSquare() {
    Board board = this.game.getBoard();
    return board.getSquare(this.getPosition());
  }

  public int getLastRollTotal() {
    return this.doubleDice.getLastRollTotal();
  }
  
  public boolean goesAgain() {
    return this.doubleDice.wasLastRollDouble() && !this.inJail;
  }

}
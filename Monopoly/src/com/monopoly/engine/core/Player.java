package com.monopoly.engine.core;

import java.util.ArrayList;
import java.util.List;

import com.monopoly.engine.cards.Card;
import com.monopoly.engine.cards.NegotiableCard;
import com.monopoly.engine.cards.PropertyCard;
import com.monopoly.engine.cards.TerrainCard;
import com.monopoly.engine.cards.chancecards.ChanceCard;
import com.monopoly.engine.cards.chancecards.JailPassChanceCard;
import com.monopoly.engine.squares.Square;
import com.monopoly.ui.UserInterface;

public class Player implements Entity {
  private Game game;
  private PlayerColor color;
  private int position;
  private int money;
  private List<NegotiableCard> cards;
  private boolean inJail;
  private int turnsInJail;
  private DoubleDice doubleDice;
  private boolean inGame;
  private List<Debt> debts;

  public Player(PlayerColor color, Game game) {
    this.doubleDice = new DoubleDice();
    this.game = game;
    this.color = color;
    this.position = 0;
    this.money = (8*1) + (10*5) + (10*10) + (10*50) + (8*100) + (2*500);
    this.cards = new ArrayList<NegotiableCard>();
    this.inJail = false;
    this.turnsInJail = 0;
    this.inGame = true;
    this.debts = new ArrayList<Debt>();
  }

  public String getName() {
    return this.color.name();
  }
  
  @Override
  public String toString() {
    return this.getName();
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
  
  public int getBalance() {
    int debtSum = 0;
    for(Debt d : this.debts){
      debtSum += d.amount;
    }
    return this.money - debtSum;
  }

  public List<NegotiableCard> getCards() {
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

  public boolean owns(NegotiableCard card) {
    return this.cards.contains(card);
  }

  public boolean owns(List<TerrainCard> cards) {
    for(Card c : cards) {
      if(!this.owns((NegotiableCard)c)){
        return false;
      }
    }
    return true;
  }

  public void charge(int x){
    if(x > this.money){
      throw new IllegalArgumentException("Not enough money! Tried to charge $" + x + " from " + 
                                          this.getName() + ", who only has $" + this.money);
    }
    this.money -= x;
  }

  public void give(int x){
    this.money += x;
    this.payDebts();
  }
  
  public void give(NegotiableCard card) {
    this.cards.add(card);
    card.setOwner(this);
  }
  
  public void sellCardTo(Entity other, NegotiableCard card, int amount) {
    if(this.owns(card) && other.affords(amount)){
      this.cards.remove(card);
      other.charge(amount);
      this.give(amount);
      other.give(card);
    }
  }

  public void payTo(Entity other, int amount) {
    if(amount > this.money){
      this.addDebt(other, amount - money);
      amount = this.money;
    }
    this.charge(amount);
    other.give(amount);
  }
  
  public void addDebt(Entity other, Integer amount){
    Debt debt = new Debt(other, amount);
    this.debts.add(debt);
    this.game.getUI().showMessage(this.getName() + " deve $" + amount + " a " + other.getName());
  }
  
  public void payDebts(){
    for(Debt d : this.debts){
      if(this.money <= 0){
        break; 
      }
      
      this.debts.remove(d);
      int amount = d.amount;
      if(amount > this.money){
        this.game.getUI().showMessage(this.getName() + " pagou $" + this.money + " a " + d.other.getName());
        this.addDebt(d.other, amount - money);
        this.money = 0;
      } else {
        this.game.getUI().showMessage(this.getName() + " pagou $" + amount + " a " + d.other.getName());
        this.money -= amount;
      }
    }
  }
  
  public boolean isInDebt(){
    return this.getBalance() < 0;
  }

  public void buyProperty(PropertyCard card) {
    this.charge(card.getPrice());
    this.give(card);
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

  public void returnJailPass() {
    for(NegotiableCard c : this.cards) {
      if(c instanceof JailPassChanceCard) {
        c.setOwner(null);
        this.cards.remove(c);
        this.game.getChanceDeck().addToBottom((ChanceCard)c);
        return;
      }
    }
  }

  public boolean hasJailPass() {
    for(NegotiableCard c : this.cards) {
      if(c instanceof JailPassChanceCard) {
        return true;
      }
    }
    return false;
  }
  
  public void resetDoubleCounter(){
    this.doubleDice = new DoubleDice();
  }
  
  private void removeFromJail() {
    this.inJail = false;
    this.turnsInJail = 0;
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
    ui.showMessage(this.getName() + ", seus dados foram " + values[0] + " e " + values[1]);
    
    if(doubleDice.getDoubleCounter() == 3){
      ui.showMessage(this.getName() + ", você tirou duplas três vezes seguidas e vai para a cadeia...");
      this.goToJail();
    } else {
      this.move(steps);
    }

  }
  
  private void rollInJail() {
    UserInterface ui = this.game.getUI();
    
    int[] values = doubleDice.roll();
    int steps = doubleDice.getLastRollTotal();
    boolean wasDouble = doubleDice.wasLastRollDouble();
    
    ui.showMessage(this.getName() + " está na cadeia. Seus dados foram " + values[0] + " e " + values[1]);
    
    if(wasDouble){
      ui.showMessage(this.getName() + " saiu da cadeia!");
      this.removeFromJail();
      this.move(steps);
    } else {
      this.turnsInJail += 1;
      if(turnsInJail == 4){
        ui.showMessage(this.getName() + ", como se passaram 4 turnos, você pagará a fiança de $50 e sairá da cadeia.");
        this.payTo(this.game.getBank(), 50);
        this.removeFromJail();
        this.move(steps);
      } else {
        ui.showMessage(this.getName() + " continua na cadeia.");
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

  public void useJailPass() {
    UserInterface ui = this.game.getUI();

    if(this.hasJailPass()) {
      ui.showMessage(this.getName() + " usou o passe livre da prisão e saiu da cadeia!");
      this.returnJailPass();
      this.removeFromJail();
    }
  }

  public boolean isOutOfGame() {
    return !this.inGame;
  }

  public boolean canGoBankrupt() {
    if(this.money > 0){
      return false;
    }
    if(this.cards.size() > 0){
      return false;
    }
    return this.isInDebt();
  }

  public void removeFromGame() {
    this.inGame = false;
  }

}
package com.monopoly.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;

import com.monopoly.engine.cards.NegotiableCard;
import com.monopoly.engine.cards.PropertyCard;
import com.monopoly.engine.core.*;
import com.monopoly.ui.cardlist.CardListButton;
import com.monopoly.ui.cardlist.CardListFrame;


public class GUI extends Observable implements ActionListener, UserInterface {
  private GameFrame gameFrame;
  
  public GUI() {
    this.gameFrame = new GameFrame();
  }
  
  public void bindGame(Game game){
    this.gameFrame.bindGame(game);
    this.gameFrame.addListenerToButtons(this);
    this.addObserver(game);
  };

  public boolean askBoolean (String message) {
    return this.askBoolean(message, "Pergunta");
  }

  public boolean askBoolean (String message, String title) {
    return gameFrame.askBoolean(message, title);
  }
  
  public String askString (String message) {
    return this.askString(message, "Pergunta");
  }

  public String askString (String message, String title) {
    return gameFrame.askString(message, title);
  }
  
  public int askInt (String message, int max) {
    return this.askInt(message, "Pergunta", 1, max);
  }

  public int askInt (String message, String title, int max) {
    return this.askInt(message, title, 1, max);
  }
  
  public int askInt (String message, int min, int max) {
    return this.askInt(message, "Pergunta", min, max);
  }

  public int askInt (String message, String title, int min, int max) {
    return gameFrame.askInt(message, title, min, max);
  }

  public void showMessage (String message) {
    this.showMessage(message, "Mensagem");
  }

  public void showMessage (String message, String title) {
    this.gameFrame.showMessage(message, title);
  }
  
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    UserInterfaceEvents event = null;
    HashMap<String, Object> args = new HashMap<String, Object>();

    if ("diceButton".equals(cmd)) {
      event = UserInterfaceEvents.ROLL_DICES;
      
    } else if("passTurnButton".equals(cmd)) {
      event = UserInterfaceEvents.PASS_TURN;
      
    } else if("jailPassButton".equals(cmd)) {
      event = UserInterfaceEvents.JAIL_PASS;
      
    } else if("goBankruptButton".equals(cmd)) {
      event = UserInterfaceEvents.GO_BANKRUPT;
      
    } else if("showCardsButton".equals(cmd)) {
      new CardListFrame( ((ShowCardsButton)e.getSource()).getPlayer() );
      
    } else if("cardNegotiateButton".equals(cmd)) {
      NegotiableCard card = ((CardListButton)e.getSource()).getCard();
      event = UserInterfaceEvents.CARD_NEGOCIATE;
      args.put("card", card);
      
    } else if("cardMortgageButton".equals(cmd)) {
      NegotiableCard card = ((CardListButton)e.getSource()).getCard();
      event = UserInterfaceEvents.CARD_MORTGAGE;
      args.put("card", card);
      
    } else if("cardNewBuildingButton".equals(cmd)) {
      NegotiableCard card = ((CardListButton)e.getSource()).getCard();
      event = UserInterfaceEvents.CARD_NEW_BUILDING;
      args.put("card", card);
      
    } else if("cardPulverizeBuildingButton".equals(cmd)) {
      NegotiableCard card = ((CardListButton)e.getSource()).getCard();
      event = UserInterfaceEvents.CARD_PULVERIZE_BULDING;
      args.put("card", card);
    }
    
    if(event != null)
    {
      this.setChanged();
      this.notifyObservers(new UserInterfaceNotification(event, args));
    }
    this.gameFrame.repaint();
  }

}

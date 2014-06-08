package com.monopoly.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Observable;

import com.monopoly.engine.cards.NegotiableCard;
import com.monopoly.engine.core.*;
import com.monopoly.ui.cardlist.CardListButton;
import com.monopoly.ui.cardlist.CardListFrame;


public class GUI extends Observable implements ActionListener, WindowListener, UserInterface {
  private GameFrame gameFrame;
  private CardListFrame cardListFrame = null;
  
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
  
  public Object askOptions (String message, Object[] options) {
    return this.askOptions(message, "Pergunta", options);
  }

  public Object askOptions (String message, String title, Object[] options) {
    return gameFrame.askOptions(message, title, options);
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
    this.repaint();
  }
  
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    UserInterfaceEvents event = null;
    HashMap<String, Object> args = new HashMap<String, Object>();
    System.out.println(cmd);

    if ("diceButton".equals(cmd)) {
      event = UserInterfaceEvents.ROLL_DICES;

    } else if("viewSquareButton".equals(cmd)) {
      event = UserInterfaceEvents.VIEW_SQUARE;

    } else if("buyPropertyButton".equals(cmd)) {
      event = UserInterfaceEvents.BUY_PROPERTY;

    } else if("passTurnButton".equals(cmd)) {
      event = UserInterfaceEvents.PASS_TURN;
      
    } else if("jailPassButton".equals(cmd)) {
      event = UserInterfaceEvents.JAIL_PASS;
      
    } else if("goBankruptButton".equals(cmd)) {
      event = UserInterfaceEvents.GO_BANKRUPT;
      
    } else if("showCardsButton".equals(cmd)) {
      if(this.cardListFrame != null)
        this.cardListFrame.dispose();
      this.cardListFrame = new CardListFrame( ((ShowCardsButton)e.getSource()).getPlayer() );
      this.cardListFrame.addListenerToButtons(this);
      this.cardListFrame.addWindowListener(this);
      
    } else if("cardViewButton".equals(cmd)) {
      NegotiableCard card = ((CardListButton)e.getSource()).getCard();
      event = UserInterfaceEvents.CARD_VIEW;
      args.put("card", card);
      
    } else if("cardNegotiateButton".equals(cmd)) {
      NegotiableCard card = ((CardListButton)e.getSource()).getCard();
      event = UserInterfaceEvents.CARD_NEGOTIATE;
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
      event = UserInterfaceEvents.CARD_REMOVE_BULDING;
      args.put("card", card);
    }
    
    if(event != null)
    {
      this.setChanged();
      this.notifyObservers(new UserInterfaceNotification(event, args));
    }
    this.gameFrame.repaint();
  }
  
  public void repaint()
  {
    this.gameFrame.repaint();
    if(this.cardListFrame != null)
    {
      this.cardListFrame.update();
      this.cardListFrame.addListenerToButtons(this);
    }
  }
  
  public void windowClosing(WindowEvent e)
  {
    this.cardListFrame.dispose();
    this.cardListFrame = null;
  }
  
  public void windowOpened(WindowEvent e) {}
  public void windowClosed(WindowEvent e) {}
  public void windowIconified(WindowEvent e) {}
  public void windowDeiconified(WindowEvent e) {}
  public void windowActivated(WindowEvent e) {}
  public void windowDeactivated(WindowEvent e) {}
  

}

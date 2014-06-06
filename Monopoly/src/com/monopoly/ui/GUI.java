package com.monopoly.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import com.monopoly.engine.core.*;


public class GUI extends Observable implements ActionListener, UserInterface {
  private GameFrame gameFrame;
  
  public GUI(Game game) {
    
    this.gameFrame = new GameFrame(
        new BoardPanel(game.getPlayers()), 
        new PlayerListPanel(game));
    
    gameFrame.addListenerToButtons(this);

    this.addObserver(game);
  }

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

  public void showMessage (String message) {
    this.showMessage(message, "Mensagem");
  }

  public void showMessage (String message, String title) {
    this.gameFrame.showMessage(message, title);
  }
  
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    if ("diceButton".equals(cmd)) {
      this.setChanged();
      this.notifyObservers(UserInterfaceEvents.ROLL_DICES);
    } else if("passTurnButton".equals(cmd)) {
      this.setChanged();
      this.notifyObservers(UserInterfaceEvents.PASS_TURN);
    } else if("jailPassButton".equals(cmd)) {
      this.setChanged();
      this.notifyObservers(UserInterfaceEvents.JAIL_PASS);
    }
    this.gameFrame.repaint();
  }

}

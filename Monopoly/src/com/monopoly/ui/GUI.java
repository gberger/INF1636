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

  public boolean askMessage (String message) {
    return this.askMessage(message, "Pergunta");
  }

  public boolean askMessage (String message, String title) {
    return gameFrame.askMessage(message, title);
  }
  
  public String inputMessage (String message) {
    return this.inputMessage(message, "Pergunta");
  }

  public String inputMessage (String message, String title) {
    return gameFrame.inputMessage(message, title);
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

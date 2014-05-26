package com.monopoly.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;

import com.monopoly.engine.core.*;


public class GUI extends Observable implements ActionListener, UserInterface {
  private GameFrame gameFrame;
  
  public GUI(Game game) {
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    
    PassButton passButton = new PassButton();
    passButton.setup(this);
    buttons.add(passButton);
    
    DiceButton diceButton = new DiceButton();
    diceButton.setup(this);
    buttons.add(diceButton);

    this.gameFrame = new GameFrame(
        new BoardPanel(game.getPlayers()), 
        new PlayerListPanel(game),
        buttons);

    this.addObserver(game);
  }

  public boolean askMessage (String message) {
    return this.askMessage(message, "Pergunta");
  }

  public boolean askMessage (String message, String title) {
    return gameFrame.askMessage(message, title);
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
    } else if("passButton".equals(cmd)) {
      this.setChanged();
      this.notifyObservers(UserInterfaceEvents.PASS_TURN);
    }
    this.gameFrame.repaint();
  }

  @Override
  public void repaint() {
    gameFrame.repaint();
  }
}

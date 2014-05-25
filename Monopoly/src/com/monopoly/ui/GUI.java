package com.monopoly.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import com.monopoly.engine.core.*;


public class GUI extends Observable implements ActionListener, UserInterface {
  private GameFrame gameFrame;
  
  public GUI(Game game) {
    DiceButton diceButton = new DiceButton();
    diceButton.setup(this);

    this.gameFrame = new GameFrame(
        new BoardPanel(game.getPlayers()), 
        new PlayerListPanel(game),
        diceButton);

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
      this.notifyObservers(UserInterfaceEvents.GUI_ROLL_DICES);
    }
    this.gameFrame.repaint();
  }

  @Override
  public void repaint() {
    gameFrame.repaint();
  }
}

package com.monopoly.ui;

import javax.swing.JButton;

import com.monopoly.engine.core.Player;

public class ShowCardsButton extends JButton {
  private static final long serialVersionUID = -8997723019974446697L;
  private Player player;
  
  public ShowCardsButton(Player p) {
    super(p.getCards().size() + " cartas");
    this.player = p;
  }
  
  public ShowCardsButton setup(int x, int y){
    this.setActionCommand("showCardsButton");
    this.setBounds(x,y,80,32);
    this.setText(this.player.getCards().size() + " cartas");
    this.setEnabled(this.player.getCards().size() != 0);
    return this;
  }
  
  public Player getPlayer() {
    return this.player;
  }
}

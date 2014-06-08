package com.monopoly.ui;

import javax.swing.JButton;

public class PassTurnButton extends JButton {
  private static final long serialVersionUID = 3978429113514901928L;

  public PassTurnButton() {
    super("Passar vez");
  }
  
  public PassTurnButton setup(){
    this.setActionCommand("passTurnButton");
    this.setBounds(10,500,240,40);
    return this;
  }
}

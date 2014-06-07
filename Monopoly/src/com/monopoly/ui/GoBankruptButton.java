package com.monopoly.ui;

import javax.swing.JButton;

public class GoBankruptButton extends JButton {
  private static final long serialVersionUID = 1L;

  public GoBankruptButton() {
    super("Ir A Falencia");
  }
  
  public GoBankruptButton setup(){
    this.setActionCommand("goBankruptButton");
    this.setBounds(10,560,240,40);
    return this;
  }
}

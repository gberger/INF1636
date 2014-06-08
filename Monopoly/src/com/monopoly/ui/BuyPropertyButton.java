package com.monopoly.ui;

import javax.swing.JButton;

public class BuyPropertyButton extends JButton {
  private static final long serialVersionUID = 1L;

  public BuyPropertyButton() {
    super("Comprar propriedade");
  }
  
  public BuyPropertyButton setup(){
    this.setActionCommand("buyPropertyButton");
    this.setBounds(10,380,240,40);
    return this;
  }
}

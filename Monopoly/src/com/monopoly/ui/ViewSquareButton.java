package com.monopoly.ui;

import javax.swing.JButton;

public class ViewSquareButton extends JButton {
  private static final long serialVersionUID = 1L;

  public ViewSquareButton() {
    super("Detalhes da posição");
  }
  
  public ViewSquareButton setup(){
    this.setActionCommand("viewSquareButton");
    this.setBounds(10,320,240,40);
    return this;
  }
}

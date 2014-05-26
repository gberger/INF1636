package com.monopoly.ui;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PassButton extends JButton {
  private static final long serialVersionUID = 3978429113514901928L;

  public PassButton() {
    super("Passar vez");
  }
  
  public void setup(ActionListener gui){
    this.setActionCommand("passButton");
    this.setBounds(10,340,120,40);
    this.addActionListener(gui);
  }
}

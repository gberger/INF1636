package com.monopoly.ui;

import javax.swing.JButton;

public class JailPassButton extends JButton {
  private static final long serialVersionUID = 3978429113514901928L;

  public JailPassButton() {
    super("Usar saída livre da prisão");
  }
  
  public JailPassButton setup(){
    this.setActionCommand("jailPassButton");
    this.setBounds(10,440,240,40);
    return this;
  }
}

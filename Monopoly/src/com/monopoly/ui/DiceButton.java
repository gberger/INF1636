package com.monopoly.ui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DiceButton extends JButton {
  private static final long serialVersionUID = 3978429113514901928L;

  public DiceButton() {
    super("Rolar dados", getDiceIcon());
  }
  
  public static ImageIcon getDiceIcon() {
    ImageIcon diceIcon = new ImageIcon("img/dice_icon.png");
    Image img = diceIcon.getImage();
    Image newimg = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
    return new ImageIcon(newimg);
  }
  
  public DiceButton setup(){
    this.setActionCommand("diceButton");
    this.setBounds(10,40,240,40);
    return this;
  }
}

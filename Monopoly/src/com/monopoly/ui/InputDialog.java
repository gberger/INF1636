package com.monopoly.ui;

import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class InputDialog {
  
  private String answer = "";

  public InputDialog(Window window, String message, String title) {
    UIManager.put("OptionPane.yesButtonText", "Sim");
    UIManager.put("OptionPane.noButtonText", "Não");
    
    this.answer = (String)JOptionPane.showInputDialog(window, message, title);
  }
  
  public String getAnswer() {
    return this.answer;
  }
}

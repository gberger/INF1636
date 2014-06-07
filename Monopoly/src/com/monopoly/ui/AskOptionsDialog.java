package com.monopoly.ui;

import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class AskOptionsDialog {
  
  private String answer = "";

  public AskOptionsDialog(Window window, String message, String title, Object[] options) {
    UIManager.put("OptionPane.yesButtonText", "Sim");
    UIManager.put("OptionPane.noButtonText", "Não");
    
    this.answer = (String)JOptionPane.showInputDialog(window, message, title, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
  }
  
  public String getAnswer() {
    return this.answer;
  }
}

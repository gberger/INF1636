package com.monopoly.ui;

import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class AskOptionsDialog {
  
  private Object answer = "";

  public AskOptionsDialog(Window window, String message, String title, Object[] options) {
    UIManager.put("OptionPane.yesButtonText", "Sim");
    UIManager.put("OptionPane.noButtonText", "Não");
    
    this.answer = JOptionPane.showInputDialog(window, message, title, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
  }
  
  public Object getAnswer() {
    return this.answer;
  }
}

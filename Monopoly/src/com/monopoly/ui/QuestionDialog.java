package com.monopoly.ui;

import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class QuestionDialog {
  
  private int answer = JOptionPane.NO_OPTION;

  public QuestionDialog(Window window, String message, String title) {
    UIManager.put("OptionPane.yesButtonText", "Sim");
    UIManager.put("OptionPane.noButtonText", "Não");
    
    this.answer = JOptionPane.showConfirmDialog(window, message, title,
        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
  }
  
  public boolean getAnswer() {
    return (this.answer == JOptionPane.YES_OPTION);
  }
}

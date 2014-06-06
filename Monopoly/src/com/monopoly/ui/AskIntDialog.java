package com.monopoly.ui;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class AskIntDialog {
  
  private int answer = 0;

  public AskIntDialog(Window window, String message, String title, int max) {
    UIManager.put("OptionPane.yesButtonText", "Sim");
    UIManager.put("OptionPane.noButtonText", "Não");
   
    List<String> optionList = new ArrayList<String>();
    for(int i = 0; i < max; i++) {
      optionList.add(Integer.toString(i));
    }
    Object[] options = optionList.toArray();
    
    this.answer = Integer.parseInt((String)JOptionPane.showInputDialog(window, message, title, JOptionPane.QUESTION_MESSAGE, null, options, options[0]));
  }
  
  public int getAnswer() {
    return this.answer;
  }
}

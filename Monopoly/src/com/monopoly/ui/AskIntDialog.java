package com.monopoly.ui;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class AskIntDialog {
  
  private int answer = 0;

  public AskIntDialog(Window window, String message, String title, int min, int max) {
    UIManager.put("OptionPane.yesButtonText", "Sim");
    UIManager.put("OptionPane.noButtonText", "Não");
   
    List<String> optionList = new ArrayList<String>();
    for(int i = min; i <= max; i++) {
      optionList.add(Integer.toString(i));
    }
    Object[] options = optionList.toArray();
    
    String s = (String)JOptionPane.showInputDialog(window, message, title, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    if(s == null){
      this.answer = -1;
    } else {
      this.answer = Integer.parseInt(s);  
    }
  }
  
  public int getAnswer() {
    return this.answer;
  }
}

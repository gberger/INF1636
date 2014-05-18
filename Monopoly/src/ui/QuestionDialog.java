package ui;

import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import engine.PropertyCard;
import engine.Player;
import engine.TerrainCard;

public class QuestionDialog {
  
  private int answer = JOptionPane.NO_OPTION;

  public QuestionDialog(Window window, String message) {
    UIManager.put("OptionPane.yesButtonText", "Sim");
    UIManager.put("OptionPane.noButtonText", "Não");
    
    this.answer = JOptionPane.showConfirmDialog(window, message, "Oferta",
        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
  }
  
  public boolean getAnswer() {
    return (this.answer == JOptionPane.YES_OPTION);
  }
}

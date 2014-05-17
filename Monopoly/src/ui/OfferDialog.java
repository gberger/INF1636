package ui;

import java.awt.Window;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import engine.OwnableCard;
import engine.Player;
import engine.TerrainCard;

public class OfferDialog {
  
  private int answer = JOptionPane.NO_OPTION;

  public OfferDialog(Window window, Player player, OwnableCard card) {
    UIManager.put("OptionPane.yesButtonText", "Sim");
    UIManager.put("OptionPane.noButtonText", "Não");
    
    String message = card.getName() + "\n";
    
    if(card instanceof TerrainCard) {
      message += "(dados especificos de terreno)\n";
    } else {
      message += "(dados especificos de companhia)\n";
    }
    
    message += player.getName() + ", deseja adquirir esta carta por $" + card.getPrice() + "?\n";
    
    this.answer = JOptionPane.showConfirmDialog(window, message, "Oferta",
        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
  }
  
  public boolean offerAccepted() {
    return (this.answer == JOptionPane.YES_OPTION);
  }
}

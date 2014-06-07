package com.monopoly.ui.cardlist;

import java.awt.Insets;

import javax.swing.JButton;

import com.monopoly.engine.cards.*;
import com.monopoly.ui.*;

public class CardListButton extends JButton {
  private static final long serialVersionUID = 12373562376198L;
  protected NegotiableCard card;
  protected UserInterfaceEvents eType;
  
  public CardListButton(NegotiableCard card, UserInterfaceEvents eventType)
  {
    this.card = card;
    this.eType = eventType;
    this.setSize(40, 15);
    this.setMargin(new Insets(0, 0, 0, 0));
    
    this.setup();
  }
  
  protected void setup()
  {
    if(this.eType == UserInterfaceEvents.CARD_VIEW) {
      this.setText("Visualizar");
      this.setActionCommand("cardView");
      
    } else if(this.eType == UserInterfaceEvents.CARD_NEGOTIATE) {
      this.setText("Negociar");
      this.setActionCommand("cardNegotiate");
      
    } else if(this.eType == UserInterfaceEvents.CARD_MORTGAGE) {
      this.setText("Hipotecar");
      this.setEnabled(this.card instanceof TerrainCard || this.card instanceof CompanyCard);
      
    } else if(this.eType == UserInterfaceEvents.CARD_NEW_BUILDING) {
      this.setText("Construir imóvel");
      this.setEnabled(this.card instanceof TerrainCard);
      
    } else if(this.eType == UserInterfaceEvents.CARD_PULVERIZE_BULDING) {
      this.setText("Demolir imóvel");
      this.setEnabled(this.card instanceof TerrainCard); 
    }
  }
  
  public NegotiableCard getCard() {
    return this.card;
  }
}

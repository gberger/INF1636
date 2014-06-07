package com.monopoly.ui.cardlist;

import java.awt.Insets;

import javax.swing.JButton;

import com.monopoly.engine.cards.*;
import com.monopoly.ui.*;

public class CardListButton extends JButton {
  private static final long serialVersionUID = 12373562376198L;
  protected PropertyCard property;
  protected UserInterfaceEvents eType;
  
  public CardListButton(PropertyCard propertyCard, UserInterfaceEvents eventType)
  {
    this.property = propertyCard;
    this.eType = eventType;
    this.setSize(40, 15);
    this.setMargin(new Insets(0, 0, 0, 0));
    
    this.setup();
  }
  
  protected void setup()
  {
    if(this.eType == UserInterfaceEvents.CARD_VIEW) {
      this.setText("Visualizar");
      
    } else if(this.eType == UserInterfaceEvents.CARD_NEGOCIATE) {
      this.setText("Negociar");
      
    } else if(this.eType == UserInterfaceEvents.CARD_MORTGAGE) {
      this.setText("Hipotecar");
      
    } else if(this.eType == UserInterfaceEvents.CARD_NEW_BUILDING) {
      this.setText("Construir imóvel");
      this.setEnabled(this.property instanceof TerrainCard);
      
    } else if(this.eType == UserInterfaceEvents.CARD_PULVERIZE_BULDING) {
      this.setText("Demolir imóvel");
      this.setEnabled(this.property instanceof TerrainCard); 
    }
  }
}

package com.monopoly.ui;

import java.awt.Insets;

import javax.swing.JButton;

import com.monopoly.engine.cards.*;

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
      this.setEnabled(this.card instanceof TerrainCard || this.card instanceof CompanyCard);
      this.setActionCommand("cardViewButton");
      
    } else if(this.eType == UserInterfaceEvents.CARD_NEGOTIATE) {
      this.setText("Negociar");
      this.setActionCommand("cardNegotiateButton");
      
    } else if(this.eType == UserInterfaceEvents.CARD_MORTGAGE) {
      this.setText("Hipotecar");
      if(this.card instanceof PropertyCard) {
        this.setEnabled(true);
        if(((PropertyCard)card).isInMortgage()) {
          this.setText("Des-hipotecar");
        }
      } else {
        this.setEnabled(false);
      }
      this.setActionCommand("cardMortgageButton");
      
    } else if(this.eType == UserInterfaceEvents.CARD_NEW_BUILDING) {
      this.setText("Construir imóvel");
      this.setEnabled(this.card instanceof TerrainCard);
      this.setActionCommand("cardNewBuildingButton");
      
    } else if(this.eType == UserInterfaceEvents.CARD_REMOVE_BULDING) {
      this.setText("Demolir imóvel");
      this.setEnabled(this.card instanceof TerrainCard);
      this.setActionCommand("cardPulverizeBuildingButton");
    }
  }
  
  public NegotiableCard getCard() {
    return this.card;
  }
}

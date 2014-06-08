package com.monopoly.engine.squares;

import com.monopoly.engine.cards.Card;
import com.monopoly.engine.cards.PropertyCard;
import com.monopoly.engine.core.Entity;
import com.monopoly.engine.core.Player;
import com.monopoly.ui.UserInterface;

public abstract class PropertySquare extends Square {
  
  protected PropertyCard associatedCard;
  
  @Override
  public void affectLandingPlayer(Player player) {
    UserInterface ui = this.game.getUI();
    Entity owner = this.associatedCard.getOwner();
    PropertyCard card = (PropertyCard)associatedCard;

    if(owner == this.game.getBank()) {
      if(player.affords(card.getPrice())) {
        String message = player.getName() + ", deseja adquirir esta carta por $" + card.getPrice() + "?\n\n" + card.getInfoText();
        if(ui.askBoolean(message)) {
          player.buyProperty(card);
        }
      } else {
        ui.showMessage("Desculpe, " + player.getName() + ", mas você não tem dinheiro suficiente para tentar comprar este terreno.");
      }
    } else if(owner != player) {
      if(card.isInMortgage()) {
        int value = card.getRent(player);
        player.payTo(owner, value);
        ui.showMessage(player.getName() + " deve pagar $" + value + " a " + owner.getName());
      } else {
        ui.showMessage("A propriedade pertence a " + owner.getName() + ", mas está hipotecada.");
      }
    } else if(owner == player) {
      ui.showMessage(player.getName() + " parou em sua própria propriedade " + card.getName());
    }
    
  }

  public Card getAssociatedCard() {
    return this.associatedCard;
  }

  @Override
  public void affectPassingPlayer(Player player) {
    // do nothing
  }
}

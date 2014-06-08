package com.monopoly.engine.squares;

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

    if(owner != player && owner != this.game.getBank()) {
      if(card.isInMortgage()) {
        ui.showMessage("A propriedade pertence a " + owner.getName() + ", mas está hipotecada.");
      } else {
        int value = card.getRent(player);
        ui.showMessage(player.getName() + " deve pagar $" + value + " a " + owner.getName());
        player.payTo(owner, value);
      }
    } else if(owner == player) {
      ui.showMessage(player.getName() + " parou em sua própria propriedade " + card.getName());
    }
    
  }

  public PropertyCard getAssociatedCard() {
    return this.associatedCard;
  }

  @Override
  public void affectPassingPlayer(Player player) {
    // do nothing
  }
}

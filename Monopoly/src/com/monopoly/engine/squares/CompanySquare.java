package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.cards.CompanyCard;
import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;
import com.monopoly.ui.UserInterface;

public class CompanySquare extends Square {
  public CompanySquare(JSONObject jobj, Game game) {
    this.game = game;
    this.id = new Long((long) jobj.get("id")).intValue();
    this.associatedCard = game.getCompanyDeck().findByName((String)jobj.get("name"));
  }
  
  @Override
  public void affectLandingPlayer(Player player) {
    Player owner = game.getCardOwner(this.associatedCard);
    CompanyCard card = (CompanyCard)associatedCard;
    UserInterface ui = game.getUI();

    if(owner == null) {
      if(player.affords(card.getPrice())) {
        String message = card.getName() + "\n" + player.getName() + ", deseja adquirir esta carta por $" + card.getPrice() + "?\n";
        if(ui.askMessage(message)) {
          player.buyProperty(card);
        }
      } else {
        ui.showMessage("Desculpe, " + player.getName() + ", mas você não tem dinheiro suficiente para tentar comprar este terreno.");
      }
    } else if(owner != player) {
      int value = card.getRent(player.getLastRollTotal());
      player.payTo(owner, value);
      ui.showMessage(player.getName() + " pagou $" + value + " a " + owner.getName());
    }
    
  }

  @Override
  public void affectPassingPlayer(Player player) {
    // do nothing
  }

}

package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.cards.TerrainCard;
import com.monopoly.engine.cards.TerrainDeck;
import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;
import com.monopoly.ui.UserInterface;

public class TerrainSquare extends Square {
  public TerrainSquare(JSONObject jobj, TerrainDeck terrains) {
    this.id = new Long((long) jobj.get("id")).intValue();
    this.associatedCard = terrains.findByName((String)jobj.get("name"));
  }

  @Override
  public void affectLandingPlayer(Game game, Player player, UserInterface ui) {
    Player owner = game.getCardOwner(this.associatedCard);
    TerrainCard card = (TerrainCard)associatedCard;

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
      int value = card.getRent();
      player.payTo(owner, value);
      ui.showMessage(player.getName() + " pagou $" + value + " a " + owner.getName());
    }
    
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, UserInterface ui) {
    // do nothing
  }

}

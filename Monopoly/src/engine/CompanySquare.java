package engine;

import org.json.simple.JSONObject;

import ui.UserInterface;

public class CompanySquare extends Square {
  public CompanySquare(JSONObject jobj, CompanyDeck companies) {
    this.id = new Long((long) jobj.get("id")).intValue();
    this.associatedCard = companies.findByName((String)jobj.get("name"));
  }
  
  @Override
  public void affectLandingPlayer(Game game, Player player, UserInterface ui) {
    Player owner = game.getCardOwner(this.associatedCard);
    CompanyCard card = (CompanyCard)associatedCard;

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
      int value = card.getRent(game.getDices().getLastRollTotal());
      player.payTo(owner, value);
      ui.showMessage(player.getName() + " pagou $" + value + " a " + owner.getName());
    }
    
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, UserInterface ui) {
    // do nothing
  }

}

package engine;

import org.json.simple.JSONObject;

import ui.UI;


public class Square {
  private int id;
  private SquareType type;
  private Card associatedCard = null;
  
  public Square(JSONObject jobj, TerrainDeck terrains, CompanyDeck companies) {
    this.id = new Long((long) jobj.get("id")).intValue();
    String typeStr = (String)jobj.get("type");
    this.type = SquareType.valueOf(typeStr.toUpperCase());

    if(this.type == SquareType.TERRAIN) {
      this.associatedCard = terrains.findByName((String)jobj.get("name"));
    } else if(this.type == SquareType.COMPANY) {
      this.associatedCard = companies.findByName((String)jobj.get("name"));
    }
  }
  
  public int getId() {
    return this.id;
  }
  
  public int getPosition() {
    return this.id;
  }

  public SquareType getType() {
    return this.type;
  }

  public Card getAssociatedCard() {
    return this.associatedCard;
  }
  
  public boolean isProperty() {
    return (associatedCard instanceof CompanyCard) || (associatedCard instanceof TerrainCard);
  }

  public void affectLandingPlayer(Game game, Player player, UI ui) {
    if(this.type == SquareType.IRS) {
      player.charge(200);
    } else if(this.type == SquareType.PROFITS) {
      player.give(200);
    } else if(this.type == SquareType.GOTOJAIL) {
      player.goToJail(game.getBoard());
    } else if(this.type == SquareType.COMPANY || this.type == SquareType.TERRAIN) {
      Player owner = game.getCardOwner(this.associatedCard);
      PropertyCard card = (PropertyCard)associatedCard;

      if(owner == null) {
        if(player.affords(card.getPrice())) {
          String message = card.getName() + "\n" + player.getName() + ", deseja adquirir esta carta por $" + card.getPrice() + "?\n";

          boolean buys = ui.askMessage(message);

          if(buys) {
            player.buyProperty(card);
          }
        }

      } else if(owner != player) {
        if(this.type == SquareType.COMPANY) {
          CompanyCard ccard = (CompanyCard)card;
          int value = ccard.getRent(game.getDices().getLastRollTotal());

          player.payTo(owner, value);
          ui.showMessage(player.getName() + " pagou $" + value + " a " + owner.getName());
        } else {
          TerrainCard tcard = (TerrainCard)card;
          int value = tcard.getRent();

          player.payTo(owner, value);
          ui.showMessage(player.getName() + " pagou $" + value + " a " + owner.getName());
        }
      }

    }
  }

}

package engine;

import org.json.simple.JSONObject;

import ui.UI;


public abstract class Square {
  protected int id;
  protected Card associatedCard = null;
  
  public int getId() {
    return this.id;
  }
  
  public int getPosition() {
    return this.id;
  }

  public Card getAssociatedCard() {
    return this.associatedCard;
  }
  
  public boolean isProperty() {
    return (associatedCard instanceof CompanyCard) || (associatedCard instanceof TerrainCard);
  }

  public abstract void affectLandingPlayer(Game game, Player player, UI ui);

  public static Square fromType(String type, JSONObject jobj, CompanyDeck companies, TerrainDeck terrains) {
    if("chance".equals(type)) {
      return new ChanceSquare(jobj);
    } else if("company".equals(type)) {
      return new CompanySquare(jobj, companies);
    } else if("free".equals(type)) {
      return new FreeSquare(jobj);
    } else if("goToJail".equals(type)) {
      return new GoToJailSquare(jobj);
    } else if("irs".equals(type)) {
      return new IrsSquare(jobj);
    } else if("jail".equals(type)) {
      return new JailSquare(jobj);
    } else if("profits".equals(type)) {
      return new ProfitsSquare(jobj);
    } else if("start".equals(type)) {
      return new StartSquare(jobj);
    } else if("terrain".equals(type)) {
      return new TerrainSquare(jobj, terrains);
    }

    return null;
  }

}

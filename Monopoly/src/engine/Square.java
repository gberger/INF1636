package engine;

import org.json.simple.JSONObject;

import ui.GUI;


public abstract class Square {
  protected int id;
  protected Card associatedCard = null;

  public static Square fromType(String type, JSONObject jobj, CompanyDeck companies, TerrainDeck terrains) {
    if(type.equals("chance"))        { return new ChanceSquare(jobj); }
    else if(type.equals("company"))  { return new CompanySquare(jobj, companies); }
    else if(type.equals("free"))     { return new FreeSquare(jobj); }
    else if(type.equals("goToJail")) { return new GoToJailSquare(jobj); }
    else if(type.equals("irs"))      { return new IrsSquare(jobj); }
    else if(type.equals("jail"))     { return new JailSquare(jobj); }
    else if(type.equals("profits"))  { return new ProfitsSquare(jobj); }
    else if(type.equals("start"))    { return new StartSquare(jobj); }
    else if(type.equals("terrain"))  { return new TerrainSquare(jobj, terrains); }

    return null;
  }

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
    return (associatedCard instanceof PropertyCard);
  }

  public abstract void affectLandingPlayer(Game game, Player player, GUI ui);

  public abstract void affectPassingPlayer(Game game, Player player, GUI ui);

}

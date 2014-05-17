package engine;

import org.json.simple.JSONObject;


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
  
  public Card getAssociatedCard() {
    return this.associatedCard;
  }
  
  public boolean isProperty() {
    return (this.getAssociatedCard() instanceof PropertyCard);
  }
}

package engine;

import org.json.simple.JSONObject;


public class Square {
  private int id;
  private Card associatedCard = null;
  
  public Square(JSONObject jobj, TerrainDeck terrains, CompanyDeck companies) {
    this.id = new Long((long) jobj.get("id")).intValue();
    if(jobj.get("type").equals("terrain"))
      this.associatedCard = terrains.findByName((String)jobj.get("name"));
    else if(jobj.get("type").equals("company"))
      this.associatedCard = companies.findByName((String)jobj.get("name"));
    // TODO: should different types be subclasses or an enum?
    // TODO: set associatedCard
    
  }
  
  public int getId() {
    return this.id;
  }
  
  public Card getAssociatedCard() {
    return this.associatedCard;
  }
  
  public boolean isOwnable() {
    return (this.getAssociatedCard() instanceof OwnableCard);
  }
}

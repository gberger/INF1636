import org.json.simple.JSONObject;


public class Square {
  private int id;
  private Card associatedCard;
  
  public Square(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
    // TODO: should different types be subclasses or an enum?
    // TODO: set associatedCard
    
  }
}

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class TerrainCard extends Card {
  private int id;
  private String name;
  private String color;
  private int buildings;
  private ArrayList rent;
  private int houseCost;
  private int hotelCost;
  private int mortgage;

  public TerrainCard(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
    this.name = (String) jobj.get("name");
    this.color = (String) jobj.get("color");
    this.buildings = 0;
    this.rent = (ArrayList) jobj.get("rent");
    this.houseCost = new Long((long) jobj.get("house")).intValue();
    this.hotelCost = new Long((long) jobj.get("hotel")).intValue();
    this.mortgage = new Long((long) jobj.get("mortgage")).intValue();
  }

  @Override
  public void affectLandingPlayer(Player player) {
    // TODO Auto-generated method stub
    
  }
}

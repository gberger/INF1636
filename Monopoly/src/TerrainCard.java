import java.util.ArrayList;

import org.json.simple.JSONObject;

public class TerrainCard extends Card {
  private String name;
  private TerrainColor color;
  private int buildings;
  private ArrayList rent;
  private int houseCost;
  private int hotelCost;
  private int mortgage;

  public TerrainCard(JSONObject jobj) {
    this.name = (String) jobj.get("name");
    this.color = TerrainColor.valueOf((String) jobj.get("color"));
    this.buildings = 0;
    this.rent = (ArrayList) jobj.get("rent");
    this.houseCost = new Long((long) jobj.get("house")).intValue();
    this.hotelCost = new Long((long) jobj.get("hotel")).intValue();
    this.mortgage = new Long((long) jobj.get("mortgage")).intValue();
  }
  
  public String getName() {
    return name;
  }

  public TerrainColor getColor() {
    return color;
  }

  public int getBuildings() {
    return buildings;
  }

  public ArrayList getRent() {
    return rent;
  }

  public int getHouseCost() {
    return houseCost;
  }

  public int getHotelCost() {
    return hotelCost;
  }

  public int getMortgage() {
    return mortgage;
  }

  @Override
  public void affectLandingPlayer(Player player) {
    // TODO Auto-generated method stub
    
  }
}

package engine;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class TerrainCard extends PropertyCard {
  private TerrainColor color;
  private int buildings;
  private ArrayList rent;
  private int houseCost;
  private int hotelCost;

  public TerrainCard(JSONObject jobj) {
    this.name = (String) jobj.get("name");
    this.color = TerrainColor.valueOf((String) jobj.get("color"));
    this.buildings = 0;
    this.rent = (ArrayList) jobj.get("rent");
    this.houseCost = new Long((long) jobj.get("house")).intValue();
    this.hotelCost = new Long((long) jobj.get("hotel")).intValue();
    this.mortgage = new Long((long) jobj.get("mortgage")).intValue();
    this.price = this.mortgage*2;
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

  @Override
  public void affectLandingPlayer(Player player) {
    // TODO Auto-generated method stub
    
  }
}

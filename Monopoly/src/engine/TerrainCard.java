package engine;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TerrainCard extends PropertyCard {
  private TerrainColor color;
  private int buildings;
  private ArrayList<Integer> rentByBuildings;
  private int houseCost;
  private int hotelCost;

  public TerrainCard(JSONObject jobj) {
    this.name = (String) jobj.get("name");
    this.color = TerrainColor.valueOf((String) jobj.get("color"));
    this.buildings = 0;
    this.rentByBuildings = new ArrayList<Integer>();
    JSONArray rents = (JSONArray)jobj.get("rent");

    for(Object obj : rents) {
      int r = new Long((long) obj).intValue();
      this.rentByBuildings.add((Integer)r);
    }

    this.houseCost = new Long((long) jobj.get("house")).intValue();
    this.hotelCost = new Long((long) jobj.get("hotel")).intValue();
    this.mortgage = new Long((long) jobj.get("mortgage")).intValue();
    this.price = this.mortgage*2;
    this.buildings = 0;
  }

  public TerrainColor getColor() {
    return color;
  }

  public int getBuildings() {
    return buildings;
  }

  public ArrayList<Integer> getRentByBuildings() {
    return this.rentByBuildings;
  }

  public int getHouseCost() {
    return houseCost;
  }

  public int getHotelCost() {
    return hotelCost;
  }

  public int getRent(){
    return this.rentByBuildings.get(this.buildings);
  }

}

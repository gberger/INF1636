package com.monopoly.engine.cards;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.squares.TerrainColor;

public class TerrainCard extends PropertyCard {
  private TerrainColor color;
  private int buildings;
  private ArrayList<Integer> rentByBuildings;
  private int houseCost;
  private int hotelCost;

  public TerrainCard(JSONObject jobj, Game game) {
    this.game = game;
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
  
  public int getNextBuildingCost() {
    if(this.buildings < 4){
      return this.houseCost;
    } else {
      return this.hotelCost;
    }
  }
  
  public int getNextBuildingRemovalEarnings() {
    if(this.buildings <= 4){
      return this.houseCost;
    } else {
      return this.hotelCost;
    }
  }
  
  public boolean isFull() {
    return this.buildings >= 5;
  }
  
  public boolean isEmpty() {
    return this.buildings <= 0;
  }
  
  public boolean addBuilding() {
    if(this.isFull()){
      return false;
    } else {
      this.buildings += 1;
      return true;
    }
  }
  
  public boolean removeBuilding() {
    if(this.isEmpty()){
      return false;
    } else {
      this.buildings -= 1;
      return true;
    }
  }

  public int getRent(){
    return this.rentByBuildings.get(this.buildings);
  }
  
  public String getInfoText()
  {
    String text = "Aluguel: $" + rentByBuildings.get(0);
    for(int i = 1; i<5;i++) {
      text += "\nC/ " + i + " casa: $" + rentByBuildings.get(i);
    }
    text += "\nC/ hotel: $" + rentByBuildings.get(5);
    
    text += "\n\nCada casa: $" + houseCost;
    text += "\nHotel: $" + hotelCost;
    
    text += "\n\nHipoteca: $" + mortgage;
    
    return text;
  }

}

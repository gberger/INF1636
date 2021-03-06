package com.monopoly.engine.cards;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;
import com.monopoly.engine.squares.TerrainColor;

public class TerrainCard extends PropertyCard {
  private TerrainColor color;
  private int buildings;
  private ArrayList<Integer> rentByBuildings;
  private int houseCost;
  private int hotelCost;

  public TerrainCard(JSONObject jobj, Game game) {
    this.game = game;
    this.owner = this.game.getBank();
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

  public int getHouses() {
    return Math.min(buildings, 4);
  }

  public int getHotels() {
    return Math.max(buildings-4, 0);
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
      return this.houseCost/2;
    } else {
      return this.hotelCost/2;
    }
  }
  
  public boolean isFull() {
    return this.buildings >= 5;
  }
  
  public boolean isEmpty() {
    return this.buildings <= 0;
  }
  
  public boolean canBeMortgaged() {
    return this.isEmpty();
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

  @Override
  public int getRent(Player player) {
    return this.rentByBuildings.get(this.buildings);
  }

  
  public String getInfoText()
  {
    String text = this.getName();
    text += "\nDono: " + this.getOwner();
    text += "\nCasas: " + this.getHouses();
    text += "\nHotéis: " + this.getHotels();
    text += "\nCor: " + this.getColor().getName();
    text += "\n\nAluguel: $" + rentByBuildings.get(0);
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

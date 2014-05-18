package engine;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class CompanyCard extends PropertyCard {
  private int multiplier;

  public CompanyCard(JSONObject jobj) {
    this.name = (String) jobj.get("name");
    this.multiplier = new Long((long) jobj.get("multiplier")).intValue();
    this.mortgage = new Long((long) jobj.get("mortgage")).intValue();
    this.price = this.mortgage*2;
  }

  public int getMultiplier() {
    return multiplier;
  }

  private int getCharge(Player player) {
    return this.multiplier * player.getLastRollTotal();
  }

  public int getRent(int roll) {
    return this.multiplier * roll;
  }
 
}
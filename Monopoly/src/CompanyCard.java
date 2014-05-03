import java.util.ArrayList;

import org.json.simple.JSONObject;

public class CompanyCard extends Card {
  private String name;
  private int multiplier;
  private int mortgage;

  public CompanyCard(JSONObject jobj) {
    this.name = (String) jobj.get("name");
    this.multiplier = new Long((long) jobj.get("multiplier")).intValue();
    this.mortgage = new Long((long) jobj.get("mortgage")).intValue();
  }

  public String getName() {
    return name;
  }

  public int getMultiplier() {
    return multiplier;
  }

  public int getMortgage() {
    return mortgage;
  }

  private int getCharge(Player player) {
    return this.multiplier * player.getLastRollTotal();
  }

  @Override
  public void affectLandingPlayer(Player player) {
    // TODO Auto-generated method stub
    
  }
 
}
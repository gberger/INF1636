public class CompanyCard extends Card {
  private String name;
  private int multiplier;

  public CompanyCard(JSON json) {

  }

  public static CompanyCard get(int id) {
    
  }

  private String getName(){
    return this.name;
  }

  private void affectLandingPlayer(Player player) {
    if(owned) {
      owner.receive(player.charge(this.getCharge(player)))
    } else {
      // TODO
    }
  }

  private int getCharge(Player player) {
    return this.multiplier * player.getLastRollTotal();
  }
 
}
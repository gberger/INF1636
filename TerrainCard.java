public class TerrainCard extends Card {
  private String name;
  private int buildings;
  private int[6] rent;

  public TerrainCard(JSON json) {

  }

  public static TerrainCard get(int id) {
    
  }

  private String getName(){
    return this.name;
  }

  public void affectLandingPlayer(Player player) {
    if(owned) {
      owner.receive(player.charge(this.getRent()));
    } else {
      // TODO
    }
  }

  private int getRent(){
    return this.rent[this.buildings];
  }
}

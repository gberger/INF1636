public class ChanceCard extends Card {
  private String type;
  private int amount;

  public ChanceCard {

  }

  public static ChanceCard get() {
    
  }

  public void affectLandingPlayer(player){
    if(this.type == RECEIVE) {
      player.receive(this.amount);

    } else if(this.type == BET) {
      player.receive(this.amount*2);
      for(Player player : Game.getInstance().getAllPlayers()){
        player.charge(this.amount);
      }

    } else if(this.type == CHARGE) {
      player.charge(this.amount);

    } else if(this.type == GO_TO_JAIL) {
      Game.getInstance().sendToJail(player);

    } else if(this.type == GO_TO_START) {
      Game.getInstance().sendToStart(player);

    } else if(this.type == JAIL_PASS) {
      player.addCard(this);
      
    }
  }
}
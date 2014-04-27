abstract class Card {
  private int id;
  private boolean ownable;
  private boolean owned;
  private Player owner;

  public abstract void affectLandingPlayer(Player player);

  private int getId(){
    return this.id;
  }

  private boolean isOwnable(){
    return this.ownable;
  }

  private boolean isOwned(){
    return this.owned;
  }

  private Player getOwner(){
    return this.owner;
  }
}
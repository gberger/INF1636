public class Game {
  private int numPlayers;
  private Player[6] players;
  private Board board;
  private List<Card> chanceCards;
  private List<Card> companyCards;
  private List<Card> terrainCards;

  public Game(int numPlayers) {
    /* Initialize players. */
    if(numPlayers < 2 || numPlayers > 6) {
      throw new ArgumentError("Número de jogadores deve ser entre 2 e 6")
    }

    this.numPlayers = numPlayers;

    for(int i = 0; i < numPlayers; i++){
      this.players[i] = new Player();
    }

    /* Load JSON for game data. */
    

  }

  public void turn() {
    Player player = this.getCurrentPlayer();
    DoubleDice dd = new DoubleDice();
    int steps, position;
    Square sq;

    dd.roll();
    steps = dd.getLastRollTotal();

    while(steps--) {
      player.walk(1);

      position = player.getPosition();
      sq = board.getSquare(position);

      if(sq.hasPassEffect()) {
        sq.affectPassingPlayer(player);
      }
    }

    if(sq.hasLandingEffect()) {
      sq.affectLandingPlayer(player);
    }

  }
}
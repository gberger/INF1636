import engine.Game;
import ui.GUI;

public class Main {
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Game game = new Game();
    GUI ui = new GUI(game);
  }
}

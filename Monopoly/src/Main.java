import engine.Game;
import ui.GUI;
import ui.UserInterface;

public class Main {
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Game game = new Game();
    UserInterface ui = new GUI(game);
  }
}

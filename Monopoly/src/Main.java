import engine.Game;
import ui.UI;

public class Main {
  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Game game = new Game();
    UI ui = new UI(game);
  }
}

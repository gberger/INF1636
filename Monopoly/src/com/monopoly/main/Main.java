package com.monopoly.main;
import com.monopoly.engine.core.Game;
import com.monopoly.ui.GUI;
import com.monopoly.ui.UserInterface;

public class Main {
  public static void main(String[] args) {
    Game game = new Game();
    UserInterface ui = new GUI(game);
    game.setUI(ui);
  }
}

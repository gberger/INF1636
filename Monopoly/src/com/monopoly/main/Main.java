package com.monopoly.main;
import java.util.ArrayList;
import java.util.List;

import com.monopoly.engine.core.Game;
import com.monopoly.ui.GUI;
import com.monopoly.ui.UserInterface;

public class Main {
  public static void main(String[] args) {
    UserInterface ui = new GUI();
    
    int numPlayers = ui.askInt("Quantos jogadores?", 2, 6);
    Game game = new Game(numPlayers);
    ui.bindGame(game);
    game.bindUI(ui);
  }
}

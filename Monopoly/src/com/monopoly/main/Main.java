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
    List<String> playerNames = new ArrayList<String>();
    for(int i = 0; i < numPlayers; i++) {
      String name = ui.askString("Nome do jogador " + (i+1) + "?");
      playerNames.add(name);
    }   
    Game game = new Game(playerNames);
    ui.bindGame(game);
    game.bindUI(ui);
  }
}

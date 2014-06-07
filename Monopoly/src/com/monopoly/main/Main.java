package com.monopoly.main;

import java.util.List;

import com.monopoly.engine.cards.TerrainCard;
import com.monopoly.engine.core.Game;
import com.monopoly.engine.squares.TerrainColor;
import com.monopoly.ui.GUI;
import com.monopoly.ui.UserInterface;

public class Main {
  public static void main(String[] args) {
    UserInterface ui = new GUI();
    
    int numPlayers = ui.askInt("Quantos jogadores?", 2, 6);
    Game game = new Game(numPlayers);
    ui.bindGame(game);
    game.bindUI(ui);

/** Code to test house building */
//    List<TerrainCard> cards = game.getTerrainDeck().findByColor(TerrainColor.LIGHT_BLUE);
//    
//    for(TerrainCard card : cards)
//    {
//      game.getPlayers().get(0).buyProperty(card);
//    }
//    ui.showMessage("fake data loaded!");
  }
}

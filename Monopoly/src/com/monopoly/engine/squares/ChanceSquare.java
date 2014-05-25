package com.monopoly.engine.squares;

import org.json.simple.JSONObject;

import com.monopoly.engine.cards.chancecards.ChanceCard;
import com.monopoly.engine.cards.chancecards.ChanceDeck;
import com.monopoly.engine.core.Game;
import com.monopoly.engine.core.Player;

public class ChanceSquare extends Square {
  public ChanceSquare(JSONObject jobj, Game game) {
    this.game = game;
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Player player) {
    ChanceDeck deck = game.getChanceDeck();
    ChanceCard card = deck.draw();

    this.game.getUI().showMessage(card.getText(), card.getTitle());
    card.affectPlayer(player);

    if(card.isReaddedToDeckAfterReading()){
      deck.addToBottom(card);
    }
  }

  @Override
  public void affectPassingPlayer(Player player) {
    // do nothing
  }

}

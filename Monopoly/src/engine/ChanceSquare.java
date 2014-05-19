package engine;

import org.json.simple.JSONObject;

import ui.UserInterface;

public class ChanceSquare extends Square {
  public ChanceSquare(JSONObject jobj) {
    this.id = new Long((long) jobj.get("id")).intValue();
  }

  @Override
  public void affectLandingPlayer(Game game, Player player, UserInterface ui) {
    ChanceDeck deck = game.getChanceDeck();
    ChanceCard card = deck.draw();

    ui.showMessage(card.getText(), card.getTitle());
    card.affectPlayer(game, player, ui);

    if(card.isReaddedToDeckAfterReading()){
      deck.addToBottom(card);
    }
  }

  @Override
  public void affectPassingPlayer(Game game, Player player, UserInterface ui) {
    // do nothing
  }

}

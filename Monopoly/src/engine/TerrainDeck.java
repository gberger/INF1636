package engine;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class TerrainDeck extends Deck<TerrainCard> {

  public TerrainDeck(JSONArray arr) {
    this.cards = new ArrayList<TerrainCard>();

    for(Object obj : arr){
      JSONObject jobj = (JSONObject) obj;
      TerrainCard card = new TerrainCard(jobj);
      cards.add(card);
    }
  }

  public TerrainCard findByName(String name) {
    for(TerrainCard card : this.cards) {
      if(name.equals(card.getName()))
        return card;
    }
    return (TerrainCard)null;
  }

}

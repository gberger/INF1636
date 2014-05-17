package engine;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ChanceDeck extends Deck<ChanceCard> {
  
  public ChanceDeck(JSONArray arr) {
    this.cards = new ArrayList<ChanceCard>();
    
    for(Object obj : arr){
      JSONObject jobj = (JSONObject) obj;
      ChanceCard card = new ChanceCard(jobj);
      cards.add(card);
    }
  }

}

package engine;

import java.util.ArrayList;
import java.util.Collections;

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

    Collections.shuffle(cards);
  }

  public ChanceCard draw(){
    return this.cards.remove(this.size() - 1);
  }

  public void addToBottom(ChanceCard card){
    this.cards.add(0, card);
  }

}

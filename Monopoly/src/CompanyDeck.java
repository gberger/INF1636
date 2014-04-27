import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class CompanyDeck extends Deck<CompanyCard> {

  public CompanyDeck(JSONArray arr) {
    this.cards = new ArrayList<CompanyCard>();
    
    for(Object obj : arr){
      JSONObject jobj = (JSONObject) obj;
      CompanyCard card = new CompanyCard(jobj);
      cards.add(card);
    }
  }

}

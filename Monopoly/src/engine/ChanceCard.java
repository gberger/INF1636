package engine;

import org.json.simple.JSONObject;

public class ChanceCard extends Card {
  private String title;
  private String text;
  private String type;
  private int amount;

  public ChanceCard(JSONObject jobj) {
    this.title = (String) jobj.get("title");
    this.text = (String) jobj.get("text");
    this.type = (String) jobj.get("type");
    this.amount = new Long((long)jobj.get("amount")).intValue();
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }

  public String getType() {
    return type;
  }

  public int getAmount() {
    return amount;
  }

}
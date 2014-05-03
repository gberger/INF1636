import java.util.List;


abstract public class Deck<T> {
  protected List<T> cards;
  
  public T get(int idx) {
    return cards.get(idx);
  }
  
  public List<T> getCards() {   
    return this.cards;
  }
  
  public int size() {
    return cards.size();
  }
  
}

package cards;
public class Card implements Comparable<Card> {
  private String name;
  private int cost;

  public Card(String name, int cost) {
    this.setName(name);
    this.setCost(cost);
  }

  protected String getName() {
    return name;
  }

  protected void setName(String name) {
    this.name = name;
  }

  protected int getCost() {
    return cost;
  }

  protected void setCost(int cost) {
    this.cost = cost;
  }

  @Override
  public String toString() {
    return String.format("Name: %s", this.name);
  }

  @Override
  public int compareTo(Card o) {
    return 0;
  }
}

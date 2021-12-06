import java.util.ArrayList;
import java.util.Collections;

import cards.Card;


public class Player implements Comparable<Player> {
  private String name;
  private int score = 0;
  private ArrayList<Card> cards;

  public Player(String name) {
    this.setName(name);
  }

  public Player(String name, ArrayList<Card> cards) {
    this.setName(name);
    this.setCards(cards);
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public void setName(String value) {
    this.name = value;
  }

  public void incrementScore() {
    this.setScore(this.getScore() + 1);
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public void setCards(ArrayList<Card> cards) {
    try {
      if(cards.isEmpty() && cards.size() != 12) {
        throw new Exception("Exception 1");
      }
      this.cards = cards;
    } catch (Exception e) {
      System.out.println("\u001B[31m" + "This text is red!" + "\u001B[31m");
    }
  }

  public Card playCard(int index) {
    return cards.get(index);
  }

  public Player shuffleCards() {
    Collections.shuffle(this.getCards());
    return this;
  }

  @Override
  public String toString() {
    String s = String.format("Player %s (%d)",this.getName(), this.getScore());
    // for (Card card : this.cards) {
    //   s += String.format("\n\t- %s (%d)", card.getName(), card.getCost());
    // }
    return s;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == this) return true;
    if(!(obj instanceof Player)) return false;
    Player c = (Player)obj;
    return this.getScore() == c.getScore();
  }

  @Override
  public int compareTo(Player o) {
    return (this.getScore() < o.getScore()) ? -1 : ((this.getScore() == o.getScore()) ? 0 : 1);
  }
}

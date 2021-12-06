package cards;

import utils.Color;

public class Land extends Card {
  public static final String PREFIX = "L";
  private Color color;
  private int size;

  public Land(String name, Color color, int size) {
    super(name, 0);
    this.setColor(color);
    this.setSize(size);
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return String.format("%s-%s Color: %s Size: %d " , PREFIX, this.getName(), this.getColor(), this.getSize());
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == this) return true;
    if(!(obj instanceof Land)) return false;
    Land s = (Land)obj;
    return this.getSize() == s.getSize();
  }

  @Override
  public int compareTo(Card o) {
    Land l = (Land)o;
    return (this.getSize() < l.getSize()) ? -1 : ((this.getSize() == l.getSize()) ? 0 : 1);
  }
}

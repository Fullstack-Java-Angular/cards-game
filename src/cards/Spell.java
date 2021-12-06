package cards;


public class Spell extends Card {
  public static final String PREFIX = "S";
  private String description;
  public Spell(String name, String description ) {
    super(name, 0);
    this.setDescription(description);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return String.format("%s-%s Description: %s", PREFIX, this.getName(), this.getDescription());
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == this) return true;
    if(!(obj instanceof Spell)) return false;
    Spell c = (Spell)obj;
    return this.getDescription().length() == c.getDescription().length();
  }

  @Override
  public int compareTo(Card o) {
    Spell s = (Spell)o;
    return (this.getDescription().length() < s.getDescription().length()) ? -1 : ((this.getDescription().length() == s.getDescription().length()) ? 0 : 1);
  }
}

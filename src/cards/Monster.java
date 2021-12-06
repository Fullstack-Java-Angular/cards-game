package cards;

public class Monster extends Card  {
  public static final String PREFIX = "M";
  private int attack;
  private int life;

  public Monster(String name, int attack, int life) {
    super(name, 0);
    this.setAttack(attack);
    this.setLife(life);
  }

  public int getLife() {
    return life;
  }

  public void setLife(int life) {
    this.life = life;
  }

  public int getAttack() {
    return attack;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

  @Override
  public String toString() {
    return String.format("%s-%s Attack: %d Life: %d", PREFIX, this.getName(), this.attack, this.life);
  }

  @Override
  public boolean equals(Object obj) {
    if(obj == this) return true;
    if(!(obj instanceof Monster)) return false;
    Monster c = (Monster)obj;
    return this.getAttack() == c.getAttack();
  }

  @Override
  public int compareTo(Card o) {
    Monster m = (Monster)o;
    return (this.getAttack() < m.getAttack()) ? -1 : ((this.getAttack() == m.getAttack()) ? 0 : 1);
  }
}

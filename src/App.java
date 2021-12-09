import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import cards.*;
import utils.Color;

public class App {
    public static void main(String[] args) throws Exception {
        ArrayList<Card> cards1 = new ArrayList<Card>(12);
        cards1.add(new Monster("Monster 1", 120, 129));
        cards1.add(new Monster("Monster 2", 90, 129));
        cards1.add(new Monster("Monster 3", 20, 12));
        cards1.add(new Monster("Monster 4", 233, 200));
        cards1.add(new Monster("Monster 5", 80, 85));
        cards1.add(new Monster("Monster 6", 34, 232));
        cards1.add(new Land("Land 1", Color.RED, 292));
        cards1.add(new Land("Land 2", Color.BLUE, 121));
        cards1.add(new Land("Land 3", Color.WHITE, 332));
        cards1.add(new Spell("Spell 1", "description 1"));
        cards1.add(new Spell("Spell 2", "description 2"));
        cards1.add(new Spell("Spell 3", "description 3"));


        ArrayList<Card> cards2 = (ArrayList<Card>)cards1.clone();

        Predicate<Card> monsterType = card -> (card instanceof Monster);
        Predicate<Card> landType = card -> (card instanceof Land);
        Predicate<Card> spellType = card -> (card instanceof Spell);

        BiPredicate<Card, Card> sameType = (c1, c2) -> {
            if( monsterType.test(c1) ) {
                return monsterType.test(c1) && monsterType.test(c2);
            } else if( landType.test(c1) ) {
                return landType.test(c1) && landType.test(c2);
            } else {
                return spellType.test(c1) && spellType.test(c2);
            }
        };

        BiConsumer<Card, Card> printVersus = (c1, c2) -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(c1 + " (vs) " + c2);
        };

        Player p1 = new Player("John", cards1).shuffleCards();
        Player p2 = new Player("Mary", cards2).shuffleCards();

        for (int i = 0; i < 12; i++) {
            Card c1 = p1.playCard(i);
            Card c2 = p2.playCard(i);
            if (sameType.test(c1, c2)) {
                if(monsterType.test(c1)) {
                    Monster m1 = (Monster)c1;
                    Monster m2 = (Monster)c2;
                    printVersus.accept(m1, m2);
                    if(m1.compareTo(m2) > 0) p1.incrementScore(); // player 1 win (1)
                    else if(m1.compareTo(m2) < 0) p2.incrementScore(); // player 2 win (1)
                } else if(landType.test(c1)) {
                    Land l1 = (Land)c1;
                    Land l2 = (Land)c2;
                    printVersus.accept(l1, l2);
                    if(l1.compareTo(l2) > 0) p1.incrementScore(); // player 1 win (1)
                    else if(l1.compareTo(l2) < 0) p2.incrementScore(); // player 2 win (1)
                } else {
                    Spell s1 = (Spell)c1;
                    Spell s2 = (Spell)c2;
                    printVersus.accept(s1, s2);
                    if(s1.compareTo(s2) > 0) p1.incrementScore(); // player 1 win (1)
                    else if(s1.compareTo(s2) < 0) p2.incrementScore(); // player 2 win (description length)
                }
            } else {
                if( spellType.test(c1) &&  monsterType.or(landType).test(c2) ) {
                    Spell s1 = (Spell)c1;
                    if( monsterType.test(c2) ) {
                        Monster m2 = (Monster)c2;
                        printVersus.accept(s1, m2);
                        p1.incrementScore(); // player 1 win (1)
                    } else {
                        Land l2 = (Land)c2;
                        printVersus.accept(s1, l2);
                        p1.incrementScore(); // player 1 win (1)
                    }
                } else if(spellType.test(c2) && monsterType.or(landType).test(c1)) {
                    Spell s2 = (Spell)c2;
                    if(monsterType.test(c1)) {
                        Monster m1 = (Monster)c1;
                        printVersus.accept(m1, s2);
                        p2.incrementScore(); // player 2 win (1)
                    } else {
                        Land l1 = (Land)c1;
                        printVersus.accept(l1, s2);
                        p2.incrementScore(); // player 2 win (1)
                    }
                } else if( monsterType.test(c1) && landType.test(c2) ) {
                    Monster m1 = (Monster)c1;
                    Land l2 = (Land)c2;
                    printVersus.accept(m1, l2);
                    p1.incrementScore(); // player 1 win (1)
                } else if ( monsterType.test(c2) && landType.test(c1) ) {
                    Land l1 = (Land)c1;
                    Monster m2 = (Monster)c2;
                    printVersus.accept(l1, m2);
                    p2.incrementScore(); // player 2 win (1)
                }
            }
        }

       System.out.println( p1.compareTo(p2) > 0 ? "The winner is : " + p1 : (p1.compareTo(p2) < 0 ? "The winner is : " + p2 : "Draw" )) ;
    }
}

package org.leanpoker.player.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by János on 9/24/2016.
 */
public class Card implements Comparable<Card> {

    private static final List<String> RANKS = Collections.unmodifiableList(asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"));

    public static final List<Card> ALL_CARDS;

    static {
        List<Card> all = new LinkedList<>();
        for (Suit s : Suit.values()) {
            for (String r : RANKS) {
                Card c = new Card();
                c.suit = s;
                c.rank = r;
                all.add(c);
            }
        }
        ALL_CARDS = Collections.unmodifiableList(all);
    }

    public String rank;
    public Suit suit;

    @Override
    public int compareTo(Card o) {
        int index = RANKS.indexOf(rank.toUpperCase());
        int oIndex = RANKS.indexOf(o.rank.toUpperCase());

        return index - oIndex;
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank='" + rank + '\'' +
                ", suit=" + suit +
                '}';
    }
}

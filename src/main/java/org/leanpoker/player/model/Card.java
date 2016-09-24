package org.leanpoker.player.model;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by János on 9/24/2016.
 */
public class Card implements Comparable<Card> {

    private static final List<String> RANKS = Collections.unmodifiableList(asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"));

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

package org.leanpoker.player;

import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.GameState;
import org.leanpoker.player.model.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by János on 9/24/2016.
 */
public interface IBetStrategy extends Comparable<IBetStrategy> {
    Optional<IBet> getBet(List<Card> cards, GameState game, Player player);
    double getWeight();
    
    default int comapreTo(IBetStrategy s) {
    	if (s.getWeight() < getWeight()) {
    		return 1;
    	}
    	if (getWeight() < s.getWeight()) {
    		return -1;
    	}
    	return 0;
    }
    
    
    
    
    
    /**
     * A listaban van-e par
     *
     * @param cards
     * @return
     */
    default boolean hasPair(List<Card> cards) {
        // sorbarendezzuk es utana rank-ok szerint csoportositjuk
        Collections.sort(cards);
        final Map<String, Integer> ranks = new HashMap<>();
        cards.forEach(c -> ranks.put(c.rank, ranks.getOrDefault(c.rank, 0) + 1));
        final long pairs = ranks.values().stream().filter(i -> i == 2).count();
        return pairs > 0;
    }

    /**
     * A listaban van-e harmas
     *
     * @param cards
     * @return
     */
    default boolean hasThree(List<Card> cards) {
        // sorbarendezzuk es utana rank-ok szerint csoportositjuk
        Collections.sort(cards);
        final Map<String, Integer> ranks = new HashMap<>();
        cards.forEach(c -> ranks.put(c.rank, ranks.getOrDefault(c.rank, 0) + 1));
        final long threes = ranks.values().stream().filter(i -> i == 3).count();
        return threes > 0;
    }

    /**
     * A listaban van-e negy ugyanolyan
     *
     * @param cards
     * @return
     */
    default boolean hasFour(List<Card> cards) {
        // sorbarendezzuk es utana rank-ok szerint csoportositjuk
        Collections.sort(cards);
        final Map<String, Integer> ranks = new HashMap<>();
        cards.forEach(c -> ranks.put(c.rank, ranks.getOrDefault(c.rank, 0) + 1));
        final long threes = ranks.values().stream().filter(i -> i == 4).count();
        return threes > 0;
    }

    /**
     * Get the highest Card from Hand
     *
     * @return highest card
     */
    default Card getHighCard(List<Card> cards) {
        // sorbarendezzuk es utana rank-ok szerint csoportositjuk
        Collections.sort(cards);
        if (cards.size() > 0) {
            return cards.get(cards.size() - 1);
        }
        return null;
    }

    default Map<String, List<Card>> group(List<Card> cards) {
    	Map<String, List<Card>> groups = new HashMap<>();

        for (Card card : cards) {
            List<Card> groupCards;
            if (groups.containsKey(card.rank)) {
                groupCards = groups.get(card.rank);
            } else {
                groupCards = new LinkedList<>();
                groups.put(card.rank, groupCards);
            }
            groupCards.add(card);
        }

        System.err.println(groups);
        return groups;
    }
}

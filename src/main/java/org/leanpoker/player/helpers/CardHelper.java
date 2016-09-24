package org.leanpoker.player.helpers;

import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.Suit;

import java.util.*;

/**
 * Created by János on 9/24/2016.
 */
public class CardHelper {

    public static Map<String, List<Card>> group(Card... cards) {
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

    public static int maxSameRank(Card... cards) {
        Set<Map.Entry<String, List<Card>>> groups = group(cards).entrySet();
        int maxSameRank = 0;
        for (Map.Entry<String, List<Card>> e : groups) {
            List<Card> groupCards = e.getValue();
            if (maxSameRank < groupCards.size()) {
                maxSameRank = groupCards.size();
            }
        }
        System.err.println("maxSameRank : " + maxSameRank);
        return maxSameRank;
    }

    public static boolean hasPair(Card... cards) {
        return 2 == maxSameRank(cards);
    }

    public static boolean hasTriple(Card... cards) {
        return 3 == maxSameRank(cards);
    }

    public static boolean hasPoker(Card... cards) {
        return 4 == maxSameRank(cards);
    }

    public static boolean allSameSuit(Card... cards) {
        if (0 >= cards.length) {
            return false;
        }
        Suit suit = cards[0].suit;
        boolean allSameSuit = Arrays.asList(cards).stream().allMatch(c -> c.suit == suit);
        return allSameSuit;
    }
}

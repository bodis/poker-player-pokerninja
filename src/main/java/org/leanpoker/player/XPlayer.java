package org.leanpoker.player;

import com.google.gson.JsonElement;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.GameState;
import org.leanpoker.player.model.Player;

import java.util.*;

public class XPlayer {

    static final String VERSION = "7";

    public static int betRequest(GameState game) {
        // ez az ellenorzes holddown-ra nem mukodik mert mindenkinel megjelenneka kartyak
        final Player player = game.players.stream().filter(p -> p.hole_cards != null && p.hole_cards.size() > 0).findFirst().orElseGet(null);
        // nem talaljuk meg a csapatunk?
        System.err.println("csapatunk: " + player);
        if (player == null) {
            return game.current_buy_in;
        }

        // kartyaink
        System.err.println("sajat kartyak: " + player.hole_cards);
        System.err.println("kozos kartyak: " + game.community_cards);

        // PAR eseten max tet
        if (hasPair(player.hole_cards)) {
            System.err.println("van par");
            return player.stack;
        }


        //
        // kozos lapokkal egyuttes szabalyok
        //
        final List<Card> mergedCards = new ArrayList<>();
        mergedCards.addAll(player.hole_cards);
        if (game.community_cards != null) {
            mergedCards.addAll(game.community_cards);
        }

        // 3-4nel ALL-IN
        if (hasThree(mergedCards) || hasFour(mergedCards)) {
            System.err.println("kozossel egyutt > van harmas/negyes: " + Arrays.toString(mergedCards.toArray()));
            return player.stack;
        }

        final Card highCard = getHighCard(player.hole_cards);
        System.err.println("magas kartyank: " + highCard);
        if (highCard.rank.equalsIgnoreCase("10") || highCard.rank.equalsIgnoreCase("J") || highCard.rank.equalsIgnoreCase("Q") || highCard.rank.equalsIgnoreCase("K") || highCard.rank.equalsIgnoreCase("A")) {
            System.err.println("elfogadott magas kartya: " + highCard);
            return game.current_buy_in;
        }

        return 0;
    }

    public static void showdown(JsonElement game) {
    }

    /**
     * A listaban van-e par
     *
     * @param cards
     * @return
     */
    private static boolean hasPair(List<Card> cards) {
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
    private static boolean hasThree(List<Card> cards) {
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
    private static boolean hasFour(List<Card> cards) {
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
    public static Card getHighCard(List<Card> cards) {
        // sorbarendezzuk es utana rank-ok szerint csoportositjuk
        Collections.sort(cards);
        if (cards.size() > 0) {
            return cards.get(0);
        }
        return null;
    }
    // final Stream<Card> sortedCards = Stream.concat(flop.stream(), cards.stream()).sorted(byRankComparator);

}

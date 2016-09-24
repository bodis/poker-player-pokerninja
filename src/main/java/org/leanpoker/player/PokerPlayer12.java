package org.leanpoker.player;

import com.google.gson.JsonElement;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.GameState;
import org.leanpoker.player.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by János on 9/24/2016.
 */
public class PokerPlayer12 implements IPlayer {

    public static final String VERSION = "12";

    public PokerPlayer12() {
    }

    public String getVersion() {
        return VERSION;
    }

    public int betRequest(GameState game) {
        // ez az ellenorzes holddown-ra nem mukodik mert mindenkinel megjelenneka kartyak
        final Player player = game.players.stream().filter(p -> p.hole_cards != null && p.hole_cards.size() > 0).findFirst().orElseGet(null);
        // nem talaljuk meg a csapatunk?
        System.err.println("csapatunk: " + player);
        if (player == null) {
            return game.current_buy_in;
        }

        // kartyaink
        System.err.println("kor: " + game.round);
        System.err.println("bet_index: " + game.bet_index);
        System.err.println("orbits: " + game.orbits);
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

        // 2-nel emeles
        if (hasPair(mergedCards)) {
            System.err.println("kozossel egyutt > par: " + Arrays.toString(mergedCards.toArray()));
            return player.stack;
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
            // ha nagyot emeltek
            if (game.current_buy_in > 100) {
                // + elso kor -> kilepunk
                if (game.community_cards != null && game.community_cards.size() > 0) {
                    return 0;
                }
                // meg nincs nagy tetunk -> kilepunk
                else if (player.bet < 100) {
                    return 0;
                }
            }

            return game.current_buy_in;
        }

        return 0;
    }

    public void showdown(JsonElement game) {
    }
}

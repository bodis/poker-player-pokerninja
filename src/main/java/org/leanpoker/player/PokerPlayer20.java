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
public class PokerPlayer20 implements IPlayer {

    public static final String VERSION = "20b";

    private int threshold = 100;

    public PokerPlayer20() {
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

        // PAR van a kezunkben
        if (hasPair(player.hole_cards)) {
            final Card card = player.hole_cards.get(0);
            System.err.println("van par > " + card);
            // elso korben ha nagy lapunk van emelunk max/2
            if (game.orbits < 2) {
                // ha a kezben magas parunk van + elso korben vagyunk, akkor max/4
                if (card.rank.equalsIgnoreCase("J") || card.rank.equalsIgnoreCase("Q") || card.rank.equalsIgnoreCase("K") || card.rank.equalsIgnoreCase("A")) {
                    System.err.println("elso korben vagyunk + parunk van kezben + magas par");
                    return (int) Math.max(Math.round((double) player.stack / 4), game.current_buy_in - player.bet);
                } else {
                    // alacsony parnal csak tartunk
                    System.err.println("elso korben vagyunk + parunk van kezben + alacsony par");
                    return Math.min(0, game.current_buy_in - player.bet);
                }
            }

            // nem elso korben vagyunk
            else {
                // de meg mindig csak parunk van es eros, akkor megadjuk
                if (card.rank.equalsIgnoreCase("J") || card.rank.equalsIgnoreCase("Q") || card.rank.equalsIgnoreCase("K") || card.rank.equalsIgnoreCase("A")) {
                    return game.current_buy_in - player.bet;
                }
                // nem kockaztatunk
                else {
                    // TODO: egy szint felett
                    return 0;
                }
            }
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
            int mennyitKellBerakni = Math.max(0, game.current_buy_in - player.bet);
            int eddigiPot = player.bet;
            if (eddigiPot<200 && mennyitKellBerakni>400) {
                return 0;
            } else {
                return Math.max(0, game.current_buy_in - player.bet);
            }
        }

        // 3-4nel ALL-IN
        if (hasThree(mergedCards) || hasFour(mergedCards)) {
            System.err.println("kozossel egyutt > van harmas/negyes: " + Arrays.toString(mergedCards.toArray()));
            // de ha a par a kozos reszekben van, akkor az nem er semmit ezert max csak megadjuk
            if (hasPair(game.community_cards) || hasThree(game.community_cards) || hasFour(game.community_cards)) {
                System.err.println("kozossel egyutt > csak a kozos lapokbol vagyunk jok");
                return Math.max(0, game.current_buy_in - player.bet);
            } else {
                return player.stack;
            }
        }


        // CSAK MAGAS KARTYANK VAN
        /*
            - ha csak magas kartyank van es valaki nagyot emel + elso korben vagyunk -> ELDOBJUK
            - ha csak magas kartyank van es valaki nagyot emel + meg nem kockaztattunk nagyot -> ELDOBJUK

         */
        final Card highCard = getHighCard(player.hole_cards);
        System.err.println("magas kartyank: " + highCard);
        if (highCard.rank.equalsIgnoreCase("10") || highCard.rank.equalsIgnoreCase("J") || highCard.rank.equalsIgnoreCase("Q") || highCard.rank.equalsIgnoreCase("K") || highCard.rank.equalsIgnoreCase("A")) {
            System.err.println("elfogadott magas kartya: " + highCard);
            // ha nagyot emeltek
            if (game.current_buy_in > threshold) {
                // elso korben vagyunk meg csak -> kilepunk
                if (game.community_cards != null && game.community_cards.size() > 0) {
                    return 0;
                }
                // meg nincs nagy tetunk -> kilepunk
                else if (player.bet < threshold) {
                    return 0;
                }
            }

            // kulonben csak megadunk
            return Math.max(0, game.current_buy_in - player.bet);
        }

        return 0;
    }

    public void showdown(JsonElement game) {
    }
}

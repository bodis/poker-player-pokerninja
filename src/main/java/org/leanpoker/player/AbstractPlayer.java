package org.leanpoker.player;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.GameState;
import org.leanpoker.player.model.Player;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;

public abstract class AbstractPlayer implements IPlayer {

	private List<IBetStrategy> strategies = new LinkedList<>();
	
	public AbstractPlayer addStrategy(IBetStrategy strategy) {
		strategies.add(strategy);
		return this;
	}
	
	@Override
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

        
//        List<IBet> bets = 
        List<Card> allCards = Lists.newLinkedList(player.hole_cards);

        List<IBet> bets = calculateBets(allCards);

        allCards.addAll(game.community_cards);

        bets.addAll(calculateBets(allCards));
        
        Collections.sort(bets);

        if (bets.isEmpty()) { return 0; }
        
		return bets.listIterator().next().getBet();
	}

	private List<IBet> calculateBets(List<Card> cards) {
		return strategies.stream()
        	.map(s -> { System.err.println("strategy : " + s.getClass().getName()); return s;})
        	.map(s -> s.getBet(cards))
        	.filter(b -> b.isPresent())
        	.map(Optional::get)
        	.collect(toList());
	}

}

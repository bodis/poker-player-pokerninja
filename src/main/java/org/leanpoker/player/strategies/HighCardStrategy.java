package org.leanpoker.player.strategies;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.leanpoker.player.AbstractBetStrategy;
import org.leanpoker.player.IBet;
import org.leanpoker.player.IBetStrategy;
import org.leanpoker.player.helpers.CardHelper;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.GameState;
import org.leanpoker.player.model.Player;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class HighCardStrategy extends AbstractBetStrategy {


	@Override
	public Optional<IBet> getBet(List<Card> cards, GameState game, Player player) {
		
		LinkedList<Card> orderedCards = Lists.newLinkedList(cards);
		
		Collections.sort(orderedCards);
		
		Card playerHighCard = getHighCard(player.hole_cards);
		
		
		String rank = orderedCards.getLast().rank;
		System.err.println(getClass().getName() + " - rank : " + rank);
		int rankIndex = Card.RANKS.indexOf(rank);
		System.err.println(getClass().getName() + " - rankIndex : " + rankIndex);
		if (rankIndex > 9) {
			if (2 < cards.size() && 0 < playerHighCard.compareTo(getHighCard(cards))) {
				return Optional.empty();
			}
		}
		
		return Optional.empty();
	}

	@Override
	public double getWeight() {
		return 0.5;
	}

}

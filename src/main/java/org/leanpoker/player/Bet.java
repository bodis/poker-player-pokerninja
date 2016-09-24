package org.leanpoker.player;

import java.util.List;

import org.leanpoker.player.model.Card;

/**
 * @author evoscja2
 *
 */
public class Bet implements IBet {

	int bet;
	IBetStrategy strategy;
	List<Card> cards;
	
	public Bet(int bet, IBetStrategy strategy, List<Card> cards) {
		this.bet = bet;
		this.strategy = strategy;
		this.cards = cards;
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public int getWeight() {
		return (int) strategy.getWeight();
	}

	@Override
	public IBetStrategy getStrategy() {
		return strategy;
	}

	@Override
	public List<Card> getCards() {
		return cards;
	}

	@Override
	public String toString() {
		return "Bet [bet=" + bet + ", strategy=" + strategy + ", cards=" + cards + "]";
	}
}

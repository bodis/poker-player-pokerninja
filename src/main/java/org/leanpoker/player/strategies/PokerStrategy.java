package org.leanpoker.player.strategies;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.leanpoker.player.AbstractBetStrategy;
import org.leanpoker.player.Bet;
import org.leanpoker.player.IBet;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.GameState;
import org.leanpoker.player.model.Player;

public class PokerStrategy extends AbstractBetStrategy {

	@Override
	public Optional<IBet> getBet(List<Card> cards, GameState game, Player player) {

		if (hasFour(cards)) {
			if (player.hole_cards.get(0).rank.equals(player.hole_cards.get(1).rank)) {
				return Optional.of(new Bet(player.stack, this, cards));
			}
			
		}
		
		return super.getBet(cards, game, player);
	}
	
	@Override
	public double getWeight() {
		return 5;
	}

}

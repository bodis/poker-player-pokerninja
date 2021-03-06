package org.leanpoker.player.strategies;

import java.util.List;
import java.util.Optional;

import org.leanpoker.player.AbstractBetStrategy;
import org.leanpoker.player.Bet;
import org.leanpoker.player.IBet;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.GameState;
import org.leanpoker.player.model.Player;

public class OnePairStrategy extends AbstractBetStrategy {


	@Override
	public Optional<IBet> getBet(List<Card> cards, GameState game, Player player) {
		if (hasPair(player.hole_cards)) {
			// par a kezben
			
			return Optional.of(new Bet(player.stack / 4, this, cards));
			
		} else if (hasPair(cards)) {
//			if (game.orbits < 2) {
//				
//			}
		}
		return Optional.empty();
	}

	@Override
	public double getWeight() {
		return 1;
	}

}

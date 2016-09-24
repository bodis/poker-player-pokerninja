package org.leanpoker.player.strategies;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.leanpoker.player.AbstractBetStrategy;
import org.leanpoker.player.IBet;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.GameState;
import org.leanpoker.player.model.Player;

public class FullHouseStrategy extends AbstractBetStrategy {

	@Override
	public Optional<IBet> getBet(List<Card> cards, GameState game, Player player) {

		Map<String, List<Card>> group = group(cards);

		
		long numberOfTwoParis = group.entrySet().stream().filter(e -> e.getValue().size() == 2).count();
		long numberOfThreeParis = group.entrySet().stream().filter(e -> e.getValue().size() == 3).count();
		
		if (0 != numberOfTwoParis && 0 != numberOfThreeParis) {
			
		}

		
		return super.getBet(cards, game, player);
	}
	
	@Override
	public double getWeight() {
		return 4;
	}

}

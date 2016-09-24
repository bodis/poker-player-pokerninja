package org.leanpoker.player;

import java.util.List;
import java.util.Optional;

import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.GameState;
import org.leanpoker.player.model.Player;

public abstract class AbstractBetStrategy implements IBetStrategy {

	@Override
	public Optional<IBet> getBet(List<Card> cards, GameState game, Player player) {
		return Optional.empty();
	}

	@Override
	public int compareTo(IBetStrategy o) {
		return ((IBetStrategy) this).compareTo(o);
	}

	
}

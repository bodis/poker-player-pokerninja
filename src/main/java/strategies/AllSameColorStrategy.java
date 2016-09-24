package strategies;

import java.util.List;
import java.util.Optional;

import org.leanpoker.player.AbstractBetStrategy;
import org.leanpoker.player.IBet;
import org.leanpoker.player.IBetStrategy;
import org.leanpoker.player.helpers.CardHelper;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.GameState;
import org.leanpoker.player.model.Player;
import org.leanpoker.player.model.Suit;

public class AllSameColorStrategy extends AbstractBetStrategy {


	@Override
	public Optional<IBet> getBet(List<Card> cards, GameState game, Player player) {

		Suit suit = cards.get(0).suit;
		
		if (cards.stream().map(c -> c.suit).allMatch(suit::equals)) {
			
		}
		
		return Optional.empty();
	}

	@Override
	public double getWeight() {
		return 1;
	}

}

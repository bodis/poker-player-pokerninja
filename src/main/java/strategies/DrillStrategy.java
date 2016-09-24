package strategies;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.leanpoker.player.AbstractBetStrategy;
import org.leanpoker.player.IBet;
import org.leanpoker.player.model.Card;
import org.leanpoker.player.model.GameState;
import org.leanpoker.player.model.Player;

public class DrillStrategy extends AbstractBetStrategy {

	@Override
	public Optional<IBet> getBet(List<Card> cards, GameState game, Player player) {

		
		if (hasThree(cards)) {
			
		}
		
		return super.getBet(cards, game, player);
	}
	
	@Override
	public double getWeight() {
		return 3;
	}

}

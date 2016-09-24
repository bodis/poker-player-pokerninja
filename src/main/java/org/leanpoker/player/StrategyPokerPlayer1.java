package org.leanpoker.player;

import org.leanpoker.player.strategies.AllSameColorStrategy;
import org.leanpoker.player.strategies.DrillStrategy;
import org.leanpoker.player.strategies.FullHouseStrategy;
import org.leanpoker.player.strategies.HighCardStrategy;
import org.leanpoker.player.strategies.OnePairStrategy;
import org.leanpoker.player.strategies.PokerStrategy;
import org.leanpoker.player.strategies.TwoPairStrategy;

import com.google.gson.JsonElement;

public class StrategyPokerPlayer1 extends AbstractPlayer {

	public StrategyPokerPlayer1() {
		addStrategy(new HighCardStrategy());
		addStrategy(new OnePairStrategy());
		addStrategy(new TwoPairStrategy());
		addStrategy(new DrillStrategy());
		addStrategy(new FullHouseStrategy());
		addStrategy(new PokerStrategy());
		addStrategy(new AllSameColorStrategy());
	}
	
	@Override
	public void showdown(JsonElement game) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getVersion() {
		return "sp - 1";
	}

}

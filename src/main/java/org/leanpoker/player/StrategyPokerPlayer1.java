package org.leanpoker.player;

import com.google.gson.JsonElement;

import strategies.AllSameColorStrategy;
import strategies.DrillStrategy;
import strategies.FullHouseStrategy;
import strategies.OnePairStrategy;
import strategies.PokerStrategy;
import strategies.TwoPairStrategy;

public class StrategyPokerPlayer1 extends AbstractPlayer {

	public StrategyPokerPlayer1() {
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

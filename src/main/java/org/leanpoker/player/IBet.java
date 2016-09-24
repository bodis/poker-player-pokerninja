package org.leanpoker.player;

import java.util.List;

import org.leanpoker.player.model.Card;

/**
 * Created by János on 9/24/2016.
 */
public interface IBet extends Comparable<IBet> {
    int getBet();
    int getWeight();
    IBetStrategy getStrategy();
    List<Card> getCards();
    
    public default int compareTo(IBet b) {
    	return getStrategy().compareTo(b.getStrategy());
    }
}

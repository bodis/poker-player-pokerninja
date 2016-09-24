package org.leanpoker.player;

import org.leanpoker.player.model.Card;

import java.util.List;

/**
 * Created by János on 9/24/2016.
 */
public interface IBetStrategy {
    IBet getBet(List<Card> cards);
}

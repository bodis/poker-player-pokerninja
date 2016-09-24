package org.leanpoker.player;

import org.leanpoker.player.model.Card;

import java.util.List;
import java.util.Optional;

/**
 * Created by János on 9/24/2016.
 */
public interface IBetStrategy {
    Optional<IBet> getBet(List<Card> cards);
}

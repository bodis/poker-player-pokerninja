package org.leanpoker.player;

import com.google.gson.JsonElement;
import org.leanpoker.player.model.GameState;

/**
 * Created by János on 9/24/2016.
 */
public interface IPlayer {
    int betRequest(GameState game);

    void showdown(JsonElement game);

    String getVersion();
}


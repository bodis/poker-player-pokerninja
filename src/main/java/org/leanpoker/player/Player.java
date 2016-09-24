package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.Map;

public class Player {

    static final String VERSION = "4";

    public static int betRequest(Map<String, Object> request) {
        Number current_buy_in = (Number) request.get("current_buy_in");
        if (current_buy_in != null) {
            return current_buy_in.intValue();
        }
        return 0;
    }

    public static void showdown(JsonElement game) {
    }
}

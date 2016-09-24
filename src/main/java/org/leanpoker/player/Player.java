package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.Map;

public class Player {

    static final String VERSION = "2";

    public static int betRequest(Map<String, Object> request) {
        Object current_buy_in = request.get("current_buy_in");
        if (current_buy_in != null) {
            return Integer.valueOf(current_buy_in.toString());
        }
        return 0;
    }

    public static void showdown(JsonElement game) {
    }
}

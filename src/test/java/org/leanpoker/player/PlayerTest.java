package org.leanpoker.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testBetRequest() throws Exception {

        JsonElement jsonElement = new JsonParser().parse("{\"key1\": \"value1\", \"key2\": \"value2\"}");
        Gson gson = new GsonBuilder().create();
        assertEquals(0, Player.betRequest(gson.fromJson(jsonElement, Map.class)));

    }
}

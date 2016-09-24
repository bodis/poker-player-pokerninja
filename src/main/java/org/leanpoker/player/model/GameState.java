package org.leanpoker.player.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by János on 9/24/2016.
 */
public class GameState {
    public List<Player> players = new LinkedList<>();
    public String tournament_id;
    public String game_id;
    public int round;
    public int bet_index;
    public int small_blind;
    public int orbits;
    public int dealer;
    public List<Card> community_cards = new LinkedList<>();
    public int current_buy_in;
    public int pot;


}

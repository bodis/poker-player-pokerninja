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
    public double round;
    public double         bet_index;
    public double         small_blind;
    public double         orbits;
    public double         dealer;
    public List<Card> community_cards = new LinkedList<>();
    public double current_buy_in;
    public double pot;


}

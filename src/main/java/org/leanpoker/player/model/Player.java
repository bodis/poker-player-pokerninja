package org.leanpoker.player.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by János on 9/24/2016.
 */
public class Player {
    public String name;
    public int stack;
    public Status status;
    public int bet;
    public List<Card> hole_cards = new LinkedList<>();
    public String version;
    public int id;

}

package org.leanpoker.player.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by János on 9/24/2016.
 */
public class Player {
    public String name;
    public double stack;
    public Status status;
    public double bet;
    public List<Card> hole_cards = new LinkedList<>();
    public String version;
    public double id;

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", stack=" + stack +
                ", status=" + status +
                ", bet=" + bet +
                ", hole_cards=" + hole_cards +
                ", version='" + version + '\'' +
                ", id=" + id +
                '}';
    }
}

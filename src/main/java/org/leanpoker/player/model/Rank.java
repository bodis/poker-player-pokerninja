package org.leanpoker.player.model;

/**
 * Card Rank
 */
public enum Rank {

    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private final String apiRank;
    private final int rankValue;

    Rank(String apiRank, int rankValue) {
        this.apiRank = apiRank;
        this.rankValue = rankValue;
    }

    public static Rank getFromAPI(String apiValue) {
        if (apiValue == null) {
            return null;
        }
        for (Rank rank : Rank.values()) {
            if (rank.apiRank.equals(apiValue)) {
                return rank;
            }
        }
        return null;
    }

    public int getRankValue() {
        return rankValue;
    }
}

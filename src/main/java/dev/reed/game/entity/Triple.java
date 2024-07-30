package dev.reed.game.entity;

import dev.reed.game.enums.PlayType;

public class Triple {

    private final PlayType first;
    private final PlayType second;
    private final PlayType winner;

    private Triple(PlayType first, PlayType second, PlayType winner) {
        this.first = first;
        this.second = second;
        this.winner = winner;
    }

    public static Triple of(PlayType first, PlayType second, PlayType winner) {
        return new Triple(first, second, winner);
    }

    public PlayType getFirst() {
        return first;
    }

    public PlayType getSecond() {
        return second;
    }

    public PlayType getWinner() {
        return winner;
    }
}

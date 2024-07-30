package dev.reed.game.entity;

public class Result {

    private Player winner;

    public Result() {
    }

    public Result(Player winner) {
        this.winner = winner;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public boolean hasWinner() {
        return winner != null;
    }
}

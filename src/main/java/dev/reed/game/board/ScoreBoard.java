package dev.reed.game.board;

public class ScoreBoard {

    private static final int FINAL_SCORE = 3;

    private int computerScore;
    private int playerScore;

    public void addComputerScore() {
        computerScore++;
    }

    public void addPlayerScore() {
        playerScore++;
    }

    public boolean reachedFinalScore() {
        return computerScore == FINAL_SCORE || playerScore == FINAL_SCORE;
    }

    public boolean isComputerWin() {
        return computerScore == FINAL_SCORE;
    }

    public void printStatistics() {
        System.out.printf("Computer: %d, player: %d%n", computerScore, playerScore);
    }
}

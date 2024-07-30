package dev.reed.game;

import dev.reed.game.board.ScoreBoard;
import dev.reed.game.entity.Player;
import dev.reed.game.entity.Result;
import dev.reed.game.enums.PlayType;
import dev.reed.game.enums.PlayerType;
import dev.reed.game.helper.GameHelper;

/**
 * Камінь, ножиці, папір:
 * <p>
 * Гравець і комп'ютер обирають один з варіантів: камінь, ножиці або папір.
 * Правила гри: камінь б'є ножиці, ножиці ріжуть папір, папір покриває камінь.
 */
public class RockPaperScissorsGame {

    private Player player;
    private Player computer;
    private ScoreBoard board;

    {
        computer = new Player();
        computer.setName("Computer");
        computer.setPlayerType(PlayerType.COMPUTER);
        board = new ScoreBoard();
    }

    public void start() {
        // Read players input
        System.out.println("Please enter your name: ");
        player = new Player();
        player.setPlayerType(PlayerType.PLAYER);
        String name = GameHelper.getInput();
        player.setName(name);
        // next logic should be run in an infinite loop
        while (true) {
            cleanPreviousRoundChoiceIfNeeded();
            System.out.println("Please enter your choice: ");
            String choice = GameHelper.getInput();
            if (choice != null && !choice.isEmpty()) {
                try {
                    PlayType playType = Enum.valueOf(PlayType.class, choice.toUpperCase());
                    player.setChoice(playType);
                } catch (Exception ex) {
                    System.err.printf("Cannot parse user input: %s%n", choice);
                    continue;
                }
            }
            PlayType randomType = GameHelper.getRandomType();
            computer.setChoice(randomType);
            Result result = GameHelper.getWinner(player, computer);
            if (result.hasWinner()) {
                Player winner = result.getWinner();
                System.out.printf("%s won this round with choice: %s!%n", winner.getName(), winner.getChoice());
                if (winner.isComputer()) {
                    board.addComputerScore();
                } else {
                    board.addPlayerScore();
                }
            } else {
                System.out.printf("It's a draw! Computer's choice: %s, player's choice: %s%n", computer.getChoice(), player.getChoice());
            }
            board.printStatistics();
            if (board.reachedFinalScore()) {
                if (board.isComputerWin()) {
                    System.out.println("Computer wins!");
                } else {
                    System.out.printf("Player %s wins!", player.getName());
                }
                break;
            }
        }
    }

    private void cleanPreviousRoundChoiceIfNeeded() {
        if (computer.getChoice() != null) {
            computer.setChoice(null);
        }
        if (player.getChoice() != null) {
            player.setChoice(null);
        }
    }

    public static void main(String[] args) {
        new RockPaperScissorsGame().start();
    }
}

package dev.reed.game.helper;

import dev.reed.game.entity.Player;
import dev.reed.game.entity.Result;
import dev.reed.game.entity.Triple;
import dev.reed.game.enums.PlayType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;

import static dev.reed.game.enums.PlayType.DRAW;
import static dev.reed.game.enums.PlayType.PAPER;
import static dev.reed.game.enums.PlayType.ROCK;
import static dev.reed.game.enums.PlayType.SCISSORS;
import static dev.reed.game.enums.PlayType.UNKNOWN;

public class GameHelper {

    private static final PlayType[] PLAY_TYPES = {ROCK, PlayType.PAPER, PlayType.SCISSORS};
    private static final Triple[] COMBINATION_PAIRS = {
            Triple.of(ROCK, SCISSORS, ROCK),
            Triple.of(ROCK, PAPER, PAPER),
            Triple.of(ROCK, ROCK, DRAW),
            Triple.of(SCISSORS, ROCK, ROCK),
            Triple.of(SCISSORS, PAPER, SCISSORS),
            Triple.of(SCISSORS, SCISSORS, DRAW),
            Triple.of(PAPER, ROCK, PAPER),
            Triple.of(PAPER, SCISSORS, SCISSORS),
            Triple.of(PAPER, PAPER, DRAW)
    };

    public static PlayType getRandomType() {
        return PLAY_TYPES[ThreadLocalRandom.current().nextInt(0, PLAY_TYPES.length)];
    }

    public static Result getWinner(Player playerOne, Player playerTwo) {
        PlayType result = getResult(playerOne.getChoice(), playerTwo.getChoice());
        return result == DRAW ?
                new Result() : result == playerOne.getChoice() ? new Result(playerOne) : new Result(playerTwo);
    }

    public static PlayType getResult(PlayType first, PlayType second) {
        PlayType result = UNKNOWN;
        for (Triple triple : COMBINATION_PAIRS) {
            if (triple.getFirst() == first && triple.getSecond() == second) {
                result = triple.getWinner();
                break;
            }
        }
        return result;
    }

    public static String getInput() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

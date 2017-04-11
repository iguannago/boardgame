package uk.co.boardgame.model;

public class Game {

    public String[][] getBoard() {
        return board;
    }

    public Result getOutcome() {
        return outcome;
    }

    private final String[][] board = {{"", "",""}, {"", "",""}, {"", "",""}};
    private final Result outcome = Result.IN_PROGRESS;

}

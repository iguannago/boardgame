package uk.co.boardgame.model;

public class Game {

    private final String[][] board;
    private final Result outcome;

    public Game() {
        this.board = new String[3][3];
        this.outcome = Result.IN_PROGRESS;
    }
}

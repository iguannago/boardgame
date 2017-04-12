package uk.co.boardgame.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameBoardSpec {

    @Test
    public void xWinsIfXOccupiesAllColsInARow() {
        assertThat(new GameBoard(new String[][]{{"X", "X", "X"},
                                            {"O", "O", ""},
                                            {"", "", ""}}).position()).isEqualTo(Outcome.X_WON);

        assertThat(new GameBoard(new String[][]{{"O", "O", ""},
                                            {"X", "X", "X"},
                                            {"", "", ""}}).position()).isEqualTo(Outcome.X_WON);

        assertThat(new GameBoard(new String[][]{{"O", "O", ""},
                                            {"", "", ""},
                                            {"X", "X", "X"}}).position()).isEqualTo(Outcome.X_WON);
    }

    @Test
    public void xWinsIfXOccupiesAllRowsInACol() {
        assertThat(new GameBoard(new String[][]{{"X", "O", ""},
                                            {"X", "O", ""},
                                            {"X", "", ""}}).position()).isEqualTo(Outcome.X_WON);

        assertThat(new GameBoard(new String[][]{{"O", "X", ""},
                                            {"O", "X", ""},
                                            {"", "X", ""}}).position()).isEqualTo(Outcome.X_WON);

        assertThat(new GameBoard(new String[][]{{"", "O", "X"},
                                            {"", "", "X"},
                                            {"", "O", "X"}}).position()).isEqualTo(Outcome.X_WON);
    }

    @Test
    public void xWinsIfXOccupiesDiagonal() {
        assertThat(new GameBoard(new String[][]{{"X", "", ""},
                                            {"O", "X", ""},
                                            {"O", "", "X"}}).position()).isEqualTo(Outcome.X_WON);

        assertThat(new GameBoard(new String[][]{{"", "O", "X"},
                                            {"O", "X", ""},
                                            {"X", "", ""}}).position()).isEqualTo(Outcome.X_WON);
    }

    @Test
    public void oWinsIfOOccupiesAllColsInARow() {
        assertThat(new GameBoard(new String[][]{{"O", "O", "O"},
                                            {"X", "X", ""},
                                            {"", "", ""}}).position()).isEqualTo(Outcome.O_WON);

        assertThat(new GameBoard(new String[][]{{"X", "X", ""},
                                            {"O", "O", "O"},
                                            {"", "", ""}}).position()).isEqualTo(Outcome.O_WON);

        assertThat(new GameBoard(new String[][]{{"X", "X", ""},
                                            {"", "", ""},
                                            {"O", "O", "O"}}).position()).isEqualTo(Outcome.O_WON);
    }

    @Test
    public void oWinsIfOOccupiesAllRowsInACol() {
        assertThat(new GameBoard(new String[][]{{"O", "X", ""},
                                            {"O", "X", ""},
                                            {"O", "", ""}}).position()).isEqualTo(Outcome.O_WON);

        assertThat(new GameBoard(new String[][]{{"X", "O", ""},
                                            {"X", "O", ""},
                                            {"", "O", ""}}).position()).isEqualTo(Outcome.O_WON);

        assertThat(new GameBoard(new String[][]{{"", "X", "O"},
                                            {"", "", "O"},
                                            {"", "X", "O"}}).position()).isEqualTo(Outcome.O_WON);
    }

    @Test
    public void oWinsIfOOccupiesDiagonal() {
        assertThat(new GameBoard(new String[][]{{"O", "", ""},
                                            {"X", "O", ""},
                                            {"X", "", "O"}}).position()).isEqualTo(Outcome.O_WON);

        assertThat(new GameBoard(new String[][]{{"", "X", "O"},
                                            {"X", "O", ""},
                                            {"O", "", ""}}).position()).isEqualTo(Outcome.O_WON);
    }

    @Test
    public void gameIsDrawnIfTheBoardIsFullAndNoPlayerHasWon() {
        String[][] board = {{"O", "X", "O"},
                            {"O", "X", "O"},
                            {"X", "O", "X"}};

        assertThat(new GameBoard(board).position()).isEqualTo(Outcome.DRAWN);
    }

    @Test
    public void gameIsInProgressIfBoardIsNotFullAndNoPlayerHasWon() {
        String[][] board = {{"O", "X", "O"},
                            {"O", "X", "O"},
                            {"X", "O", ""}};

        assertThat(new GameBoard(board).position()).isEqualTo(Outcome.IN_PROGRESS);
    }

}

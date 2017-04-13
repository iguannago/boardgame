package uk.co.boardgame.models.game;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameBoardSpec {

    @Test
    public void xWinsIfXOccupiesAllColsInARow() {
        assertThat(new GameBoard(new String[][]{{"X", "X", "X"},
                                            {"O", "O", ""},
                                            {"", "", ""}}).currentState()).isEqualTo(GameState.X_WON);

        assertThat(new GameBoard(new String[][]{{"O", "O", ""},
                                            {"X", "X", "X"},
                                            {"", "", ""}}).currentState()).isEqualTo(GameState.X_WON);

        assertThat(new GameBoard(new String[][]{{"O", "O", ""},
                                            {"", "", ""},
                                            {"X", "X", "X"}}).currentState()).isEqualTo(GameState.X_WON);
    }

    @Test
    public void xWinsIfXOccupiesAllRowsInACol() {
        assertThat(new GameBoard(new String[][]{{"X", "O", ""},
                                            {"X", "O", ""},
                                            {"X", "", ""}}).currentState()).isEqualTo(GameState.X_WON);

        assertThat(new GameBoard(new String[][]{{"O", "X", ""},
                                            {"O", "X", ""},
                                            {"", "X", ""}}).currentState()).isEqualTo(GameState.X_WON);

        assertThat(new GameBoard(new String[][]{{"", "O", "X"},
                                            {"", "", "X"},
                                            {"", "O", "X"}}).currentState()).isEqualTo(GameState.X_WON);
    }

    @Test
    public void xWinsIfXOccupiesDiagonal() {
        assertThat(new GameBoard(new String[][]{{"X", "", ""},
                                            {"O", "X", ""},
                                            {"O", "", "X"}}).currentState()).isEqualTo(GameState.X_WON);

        assertThat(new GameBoard(new String[][]{{"", "O", "X"},
                                            {"O", "X", ""},
                                            {"X", "", ""}}).currentState()).isEqualTo(GameState.X_WON);
    }

    @Test
    public void oWinsIfOOccupiesAllColsInARow() {
        assertThat(new GameBoard(new String[][]{{"O", "O", "O"},
                                            {"X", "X", ""},
                                            {"", "", ""}}).currentState()).isEqualTo(GameState.O_WON);

        assertThat(new GameBoard(new String[][]{{"X", "X", ""},
                                            {"O", "O", "O"},
                                            {"", "", ""}}).currentState()).isEqualTo(GameState.O_WON);

        assertThat(new GameBoard(new String[][]{{"X", "X", ""},
                                            {"", "", ""},
                                            {"O", "O", "O"}}).currentState()).isEqualTo(GameState.O_WON);
    }

    @Test
    public void oWinsIfOOccupiesAllRowsInACol() {
        assertThat(new GameBoard(new String[][]{{"O", "X", ""},
                                            {"O", "X", ""},
                                            {"O", "", ""}}).currentState()).isEqualTo(GameState.O_WON);

        assertThat(new GameBoard(new String[][]{{"X", "O", ""},
                                            {"X", "O", ""},
                                            {"", "O", ""}}).currentState()).isEqualTo(GameState.O_WON);

        assertThat(new GameBoard(new String[][]{{"", "X", "O"},
                                            {"", "", "O"},
                                            {"", "X", "O"}}).currentState()).isEqualTo(GameState.O_WON);
    }

    @Test
    public void oWinsIfOOccupiesDiagonal() {
        assertThat(new GameBoard(new String[][]{{"O", "", ""},
                                            {"X", "O", ""},
                                            {"X", "", "O"}}).currentState()).isEqualTo(GameState.O_WON);

        assertThat(new GameBoard(new String[][]{{"", "X", "O"},
                                            {"X", "O", ""},
                                            {"O", "", ""}}).currentState()).isEqualTo(GameState.O_WON);
    }

    @Test
    public void gameIsDrawnIfTheBoardIsFullAndNoPlayerHasWon() {
        String[][] board = {{"O", "X", "O"},
                            {"O", "X", "O"},
                            {"X", "O", "X"}};

        assertThat(new GameBoard(board).currentState()).isEqualTo(GameState.DRAWN);
    }

    @Test
    public void gameIsInProgressIfBoardIsNotFullAndNoPlayerHasWon() {
        String[][] board = {{"O", "X", "O"},
                            {"O", "X", "O"},
                            {"X", "O", ""}};

        assertThat(new GameBoard(board).currentState()).isEqualTo(GameState.IN_PROGRESS);
    }

}

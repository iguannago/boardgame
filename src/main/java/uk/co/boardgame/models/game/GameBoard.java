package uk.co.boardgame.models.game;

import java.util.Arrays;
import java.util.stream.Collectors;

class GameBoard {

    private final String[][] board;

    GameBoard() {
        this(new String[][]{{"", "", ""},
                            {"", "", ""},
                            {"", "", ""}});
    }

    /**
     * Added for Unit Testing
     */
    GameBoard(String[][] board) {
        this.board = board;
    }

    void placeSymbolAt(Symbol symbol, GameBoardLocation location) {
        board[location.zeroBasedRow()][location.zeroBasedColumn()] = symbol.name();
    }

    boolean isAvailable(GameBoardLocation location) {
        return board[location.zeroBasedRow()][location.zeroBasedColumn()].isEmpty();
    }

    GameState currentState() {
        if (occupiesARowOrCol(Symbol.X) || aDiagonalIsOccupiedBy(Symbol.X)) return GameState.X_WON;

        if (occupiesARowOrCol(Symbol.O) || aDiagonalIsOccupiedBy(Symbol.O)) return GameState.O_WON;

        if (isFull()) return GameState.DRAWN;

        return GameState.IN_PROGRESS;
    }

    private boolean isFull() {
        String collect = Arrays.stream(board).map((row) -> Arrays.stream(row).collect(Collectors.joining())).collect(Collectors.joining());
        return collect.toCharArray().length == 9;
    }

    private boolean aDiagonalIsOccupiedBy(Symbol symbol) {
        return (symbol.name().equals(board[0][0]) && symbol.name().equals(board[1][1]) && symbol.name().equals(board[2][2]))
                || (symbol.name().equals(board[0][2]) && symbol.name().equals(board[1][1]) && symbol.name().equals(board[2][0]));
    }

    private boolean occupiesARowOrCol(Symbol symbol) {
        String combinedRowOrCol = symbol.name() + symbol.name() + symbol.name();
        return combinedRowOrCol.equals(row(0)) || combinedRowOrCol.equals(row(1)) || combinedRowOrCol.equals(row(2))
                || combinedRowOrCol.equals(col(0)) || combinedRowOrCol.equals(col(1)) || combinedRowOrCol.equals(col(2));
    }

    private String col(int colNo) {
        return board[0][colNo] + board[1][colNo] + board[2][colNo];
    }

    private String row(int rowNo) {
        return board[rowNo][0] + board[rowNo][1] + board[rowNo][2];
    }
}

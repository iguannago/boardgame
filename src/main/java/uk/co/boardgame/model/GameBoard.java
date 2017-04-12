package uk.co.boardgame.model;

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

    String[][] board() {
        return board;
    }

    void set(Player player, GameBoardLocation location) {
        board[location.zeroBasedRow()][location.zeroBasedColumn()] = player.name();
    }

    boolean isAvailable(GameBoardLocation location) {
        return board[location.zeroBasedRow()][location.zeroBasedColumn()].isEmpty();
    }

    Outcome position() {
        if (occupiesARowOrCol(Player.X) || aDiagonalIsOccupiedBy(Player.X)) return Outcome.X_WON;

        if (occupiesARowOrCol(Player.O) || aDiagonalIsOccupiedBy(Player.O)) return Outcome.O_WON;

        if (isFull()) return Outcome.DRAWN;

        return Outcome.IN_PROGRESS;
    }

    private boolean isFull() {
        String collect = Arrays.stream(board).map((row) -> Arrays.stream(row).collect(Collectors.joining())).collect(Collectors.joining());
        return collect.toCharArray().length == 9;
    }

    private boolean aDiagonalIsOccupiedBy(Player player) {
        return (player.name().equals(board[0][0]) && player.name().equals(board[1][1]) && player.name().equals(board[2][2]))
                || (player.name().equals(board[0][2]) && player.name().equals(board[1][1]) && player.name().equals(board[2][0]));
    }

    private boolean occupiesARowOrCol(Player player) {
        String combinedRowOrCol = player.name() + player.name() + player.name();
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

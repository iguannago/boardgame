package uk.co.boardgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.boardgame.BusinessException;
import uk.co.boardgame.LocationUnavailableException;

public class Game {

    private final GameBoard board = new GameBoard();
    private Symbol lastMoveBy;

    @JsonProperty("board")
    public String[][] getBoard() {
        return board.board();
    }

    @JsonProperty("position")
    public GamePosition position() {
        return board.position();
    }

    public void mark(Symbol symbol, GameBoardLocation location) {
        if (lastMoveBy == null && symbol.equals(Symbol.O))
            throw new BusinessException("INVALID_FIRST_MOVE", String.format("Player %s cannot make 2 consecutive moves", symbol.name()));

        if (symbol.equals(lastMoveBy))
            throw new BusinessException("INVALID_MOVE", String.format("Player %s cannot make 2 consecutive moves", symbol.name()));

        lastMoveBy = symbol;

        if (!board.isAvailable(location)) throw new LocationUnavailableException(location);

        if (!board.position().equals(GamePosition.IN_PROGRESS))
            throw new BusinessException("GAME_OVER", String.format("Game has finished with result: %s", board.position()));

        board.set(symbol, location);
    }

}

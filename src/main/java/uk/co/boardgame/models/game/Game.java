package uk.co.boardgame.models.game;

import uk.co.boardgame.exceptions.BusinessException;
import uk.co.boardgame.exceptions.LocationUnavailableException;

public class Game {

    private final GameBoard board = new GameBoard();
    private Symbol lastMoveBy;

    public GameState currentState() {
        return board.currentState();
    }

    public void placeSymbolAt(Symbol symbol, GameBoardLocation location) {
        if (lastMoveBy == null && symbol.equals(Symbol.O))
            throw new BusinessException("INVALID_FIRST_MOVE", String.format("Player %s cannot make 2 consecutive moves", symbol.name()));

        if (symbol.equals(lastMoveBy))
            throw new BusinessException("INVALID_MOVE", String.format("Player %s cannot make 2 consecutive moves", symbol.name()));

        lastMoveBy = symbol;

        if (!board.isAvailable(location)) throw new LocationUnavailableException(location);

        if (!board.currentState().equals(GameState.IN_PROGRESS))
            throw new BusinessException("GAME_OVER", String.format("Game has finished with result: %s", board.currentState()));

        board.placeSymbolAt(symbol, location);
    }

}

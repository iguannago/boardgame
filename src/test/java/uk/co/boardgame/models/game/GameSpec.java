package uk.co.boardgame.models.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.boardgame.exceptions.BusinessException;
import uk.co.boardgame.exceptions.LocationUnavailableException;

import static org.assertj.core.api.Assertions.assertThat;

public class GameSpec {

    private Game game;

    @Before
    public void init() {
        game = new Game();
    }

    @Test(expected = LocationUnavailableException.class)
    public void playerCantMarkAnOccupiedLocation() {
        game.placeSymbolAt(Symbol.X, new GameBoardLocation(1,1));
        game.placeSymbolAt(Symbol.O, new GameBoardLocation(1,1));
    }

    @Test
    public void playerXShouldMakeTheFirstMove() {
        try {
            game.placeSymbolAt(Symbol.O, new GameBoardLocation(1,1));
            Assert.fail("should throw BusinessException(INVALID_FIRST_MOVE)");
        } catch (BusinessException exception) {
            assertThat(exception.getCode()).isEqualTo("INVALID_FIRST_MOVE");
        }
    }

    @Test
    public void playerCannotMake2ConsecutiveMoves() {
        try {
            game.placeSymbolAt(Symbol.X, new GameBoardLocation(1,1));
            game.placeSymbolAt(Symbol.X, new GameBoardLocation(1,2));
            Assert.fail("should throw BusinessException(INVALID_MOVE)");
        } catch (BusinessException exception) {
            assertThat(exception.getCode()).isEqualTo("INVALID_MOVE");
        }
    }

    @Test
    public void playerCannotMakeAMoveAfterTheGameHasCompleted() {
        try {
            game.placeSymbolAt(Symbol.X, new GameBoardLocation(1,1));
            game.placeSymbolAt(Symbol.O, new GameBoardLocation(2,2));
            game.placeSymbolAt(Symbol.X, new GameBoardLocation(1,2));
            game.placeSymbolAt(Symbol.O, new GameBoardLocation(2,1));
            game.placeSymbolAt(Symbol.X, new GameBoardLocation(1,3));
            game.placeSymbolAt(Symbol.O, new GameBoardLocation(2,3));
            Assert.fail("should throw BusinessException(GAME_OVER)");
        } catch (BusinessException exception) {
            assertThat(exception.getCode()).isEqualTo("GAME_OVER");
        }
    }

}

package uk.co.boardgame.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.boardgame.BusinessException;
import uk.co.boardgame.LocationUnavailableException;

import static org.assertj.core.api.Assertions.assertThat;

public class GameSpec {

    private Game game;

    @Before
    public void init() {
        game = new Game();
    }

    @Test(expected = LocationUnavailableException.class)
    public void playerCantMarkAnOccupiedLocation() {
        game.mark(Symbol.X, new GameBoardLocation(1,1));
        game.mark(Symbol.O, new GameBoardLocation(1,1));
    }

    @Test
    public void playerXShouldMakeTheFirstMove() {
        try {
            game.mark(Symbol.O, new GameBoardLocation(1,1));
            Assert.fail("should throw BusinessException(INVALID_FIRST_MOVE)");
        } catch (BusinessException exception) {
            assertThat(exception.getCode()).isEqualTo("INVALID_FIRST_MOVE");
        }
    }

    @Test
    public void playerCannotMake2ConsecutiveMoves() {
        try {
            game.mark(Symbol.X, new GameBoardLocation(1,1));
            game.mark(Symbol.X, new GameBoardLocation(1,2));
            Assert.fail("should throw BusinessException(INVALID_MOVE)");
        } catch (BusinessException exception) {
            assertThat(exception.getCode()).isEqualTo("INVALID_MOVE");
        }
    }

    @Test
    public void playerCannotMakeAMoveAfterTheGameHasCompleted() {
        try {
            game.mark(Symbol.X, new GameBoardLocation(1,1));
            game.mark(Symbol.O, new GameBoardLocation(2,2));
            game.mark(Symbol.X, new GameBoardLocation(1,2));
            game.mark(Symbol.O, new GameBoardLocation(2,1));
            game.mark(Symbol.X, new GameBoardLocation(1,3));
            game.mark(Symbol.O, new GameBoardLocation(2,3));
            Assert.fail("should throw BusinessException(GAME_OVER)");
        } catch (BusinessException exception) {
            assertThat(exception.getCode()).isEqualTo("GAME_OVER");
        }
    }

}

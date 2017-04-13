package uk.co.boardgame.exceptions;

import uk.co.boardgame.models.game.GameBoardLocation;

public class LocationUnavailableException extends ApplicationException {
    public LocationUnavailableException(GameBoardLocation location) {
        super("LOCATION_OCCUPIED", String.format("Location %s is already occupied", location.toString()));
    }
}

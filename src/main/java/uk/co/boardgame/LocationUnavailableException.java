package uk.co.boardgame;

import uk.co.boardgame.model.GameBoardLocation;

public class LocationUnavailableException extends ApplicationException {
    public LocationUnavailableException(GameBoardLocation location) {
        super("LOCATION_OCCUPIED", String.format("Location %s is already occupied", location.toString()));
    }
}

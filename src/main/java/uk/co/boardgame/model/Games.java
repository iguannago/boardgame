package uk.co.boardgame.model;

import uk.co.boardgame.ResourceNotFoundException;

import java.util.HashMap;
import java.util.UUID;

public class Games extends HashMap<String, Game>{
    @Override
    public Game get(Object key) {
        if(!containsKey(key)) throw new ResourceNotFoundException("GAME_NOT_FOUND", "Game not found");
        return super.get(key);
    }

    public String addNewGame() {
        String gameId = UUID.randomUUID().toString();
        super.put(gameId, new Game());
        return gameId;
    }

}

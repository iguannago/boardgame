package uk.co.boardgame.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.boardgame.model.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController("/game")
public class GameResource {
    private Map<String, Game> games = new HashMap<>();

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createAGame() {
        String gameId = UUID.randomUUID().toString();
        games.put(gameId, new Game());
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/game/"+gameId).build();
    }

}

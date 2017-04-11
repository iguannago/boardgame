package uk.co.boardgame.resources;

import javaslang.Tuple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.boardgame.model.Errors;
import uk.co.boardgame.model.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class GameResource {
    private Map<String, Game> games = new HashMap<>();

    @PostMapping("/game")
    public ResponseEntity createAGame() {
        String gameId = UUID.randomUUID().toString();
        games.put(gameId, new Game());
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/game/"+gameId).build();
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity getAGame(@PathVariable("gameId") String gameId) {
        if(!games.containsKey(gameId)) return ResponseEntity.status(404).body(new Errors(Tuple.of("GAME_NOT_FOUND", "Game not found")));
        return ResponseEntity.status(200).body(games.get(gameId));
    }

}

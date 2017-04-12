package uk.co.boardgame.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.boardgame.model.*;

@RestController
public class GameResource {
    private Games games = new Games();

    @PostMapping("/game")
    public ResponseEntity createANewGame() {
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/game/" + games.addNewGame()).build();
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity getAGame(@PathVariable("gameId") String gameId) {
        return ResponseEntity
                .status(200)
                .body(games.get(gameId));
    }

    @PostMapping("/game/{gameId}/player")
    public ResponseEntity addAPlayerToGame(@PathVariable("gameId") String gameId) {
        return ResponseEntity
                .status(201)
                .header("Location", String.format("/game/%s/player/%s", gameId, games.addPlayerToGame(gameId))).build();
    }

    @PutMapping("/game/{gameId}/player/{playerId}")
    public ResponseEntity addAPlayerToGame(@PathVariable("gameId") String gameId, @PathVariable("playerId") String playerId,
                                           @RequestBody GameBoardLocation location) {
        location.validate();
        Game game = games.get(gameId);
        game.set(Player.valueOf(playerId.toUpperCase()), location);
        return ResponseEntity
                .status(200)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new GameResult(game.state()));
    }

}

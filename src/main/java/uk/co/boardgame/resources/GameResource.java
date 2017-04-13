package uk.co.boardgame.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.boardgame.models.game.*;

@RestController
public class GameResource {
    private Games games = new Games();

    @PostMapping("/game")
    public ResponseEntity createANewGame() {
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/game/" + games.addNewGame()).build();
    }

    @GetMapping("/game/{gameId}/state")
    public ResponseEntity getAGame(@PathVariable("gameId") String gameId) {
        return ResponseEntity
                .status(200)
                .body(new GameResult(games.get(gameId).currentState()));
    }

    @PutMapping("/game/{gameId}/board/{symbol}")
    public ResponseEntity putTheSymbolOnBoard(@PathVariable("gameId") String gameId, @PathVariable("symbol") String symbol,
                                              @RequestBody GameBoardLocation location) {
        location.validate();

        games.get(gameId).placeSymbolAt(Symbol.from(symbol), location);

        return ResponseEntity.status(204).build();
    }

    private class GameResult {
        @JsonProperty
        private GameState state;
        GameResult(GameState state) {
            this.state = state;
        }
    }

}

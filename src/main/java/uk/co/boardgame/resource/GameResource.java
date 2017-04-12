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

    @PutMapping("/game/{gameId}/board/{symbol}")
    public ResponseEntity putTheSymbolOnBoard(@PathVariable("gameId") String gameId, @PathVariable("symbol") String symbol,
                                              @RequestBody GameBoardLocation location) {
        location.validate();

        games.get(gameId).mark(Symbol.from(symbol), location);

        return ResponseEntity.status(204).build();
    }

}

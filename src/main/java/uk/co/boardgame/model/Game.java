package uk.co.boardgame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.boardgame.BusinessException;
import uk.co.boardgame.LocationUnavailableException;

import java.util.ArrayList;
import java.util.List;

public class Game {

    @JsonIgnore
    private List<Player> players = new ArrayList<>();
    private Player lastMoveBy;

    private final GameBoard board = new GameBoard();

    @JsonProperty("board")
    public String[][] getBoard() { return board.board(); }

    @JsonProperty("outcome")
    private Outcome getOutcome() { return board.position(); }

    public Player addPlayer() {
        if(players.size()==2) throw new BusinessException("TOO_MANY_PLAYERS", "The game already has 2 players");
        else if (players.size() == 1) return addPlayer(Player.O);
        else return addPlayer(Player.X);
    }

    private Player addPlayer(Player player) {
        players.add(player);
        return player;
    }

    public void set(Player player, GameBoardLocation location) {
        if(lastMoveBy == null && player.equals(Player.O))
            throw new BusinessException("INVALID_FIRST_MOVE", String.format("Player %s cannot make 2 consecutive moves", player.name()));

        if(player.equals(lastMoveBy))
            throw new BusinessException("INVALID_MOVE", String.format("Player %s cannot make 2 consecutive moves", player.name()));

        lastMoveBy = player;

        if(!board.isAvailable(location)) throw new LocationUnavailableException(location);

        if(!board.position().equals(Outcome.IN_PROGRESS))
            throw new BusinessException("GAME_OVER", String.format("Game has finished with result: %s", board.position()));

        board.set(player, location);
    }

    public Outcome state() {
        return board.position();
    }
}

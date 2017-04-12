package uk.co.boardgame.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.co.boardgame.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class GameBoardLocation {

    private final Integer row;
    private final Integer column;

    @JsonCreator
    public GameBoardLocation(@JsonProperty("row") Integer row, @JsonProperty("column") Integer column) {
        this.row = row;
        this.column = column;
    }

    public void validate() {
        List<Error> errors = new ArrayList<>();

        if(row < 1 || row > 3 ) errors.add(new Error("INVALID_VALUE", "Row value should be 1, 2 or 3", "/row"));
        if(column < 1 || column > 3) errors.add(new Error("INVALID_VALUE", "Column value should 1, 2 or 3", "/column"));

        if(!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    Integer zeroBasedRow() {
        return row - 1;
    }

    Integer zeroBasedColumn() {
        return column - 1;
    }

    @Override
    public String toString() {
        return String.format("{%s,%s}", row, column);
    }
}


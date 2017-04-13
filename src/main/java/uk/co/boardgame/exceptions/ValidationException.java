package uk.co.boardgame.exceptions;

import uk.co.boardgame.models.Error;

import java.util.List;

public class ValidationException extends RuntimeException {
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> errors() {
        return errors;
    }
}

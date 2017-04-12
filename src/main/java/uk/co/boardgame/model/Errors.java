package uk.co.boardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    public Errors(String code, String message) {
        this.errors.add(new Error(code, message));
    }

    public Errors(List<Error> errors) {
        this.errors.addAll(errors);
    }

    public List<Error> getErrors() {
        return errors;
    }

    private List<Error> errors = new ArrayList<>();

}

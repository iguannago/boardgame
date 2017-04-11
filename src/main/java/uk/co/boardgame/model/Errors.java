package uk.co.boardgame.model;

import javaslang.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    public Errors(Tuple2<String, String> error) {
        this.errors.add(new Error(error._1, error._2));
    }

    public class Error {
        private final String code;
        private final String message;

        public Error(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    public List<Error> getErrors() {
        return errors;
    }

    private List<Error> errors = new ArrayList<>();

}

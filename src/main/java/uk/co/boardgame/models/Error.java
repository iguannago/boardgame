package uk.co.boardgame.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

    @JsonProperty
    private final String code;
    @JsonProperty
    private final String message;
    @JsonProperty()
    private final String path;

    public Error(String code, String message) {
        this(code, message, null);
    }

    public Error(String code, String message, String path) {
        this.code = code;
        this.message = message;
        this.path = path;
    }

}

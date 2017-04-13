package uk.co.boardgame.exceptions;

public abstract class ApplicationException extends RuntimeException {
    private final String code;

    protected ApplicationException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

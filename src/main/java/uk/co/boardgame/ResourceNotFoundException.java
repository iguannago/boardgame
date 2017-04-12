package uk.co.boardgame;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String code, String message) {
        super(code, message);
    }
}

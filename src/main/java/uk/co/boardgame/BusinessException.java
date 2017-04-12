package uk.co.boardgame;

public class BusinessException extends ApplicationException {
    public BusinessException(String code, String message) {
        super(code, message);
    }
}

package uk.co.boardgame.models.game;

import uk.co.boardgame.exceptions.BusinessException;

public enum Symbol {
    X, O;

    public static Symbol from(String symbol) {
        try {
            return Symbol.valueOf(symbol.toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new BusinessException("INVALID_SYMBOL", String.format("'%s' is a invalid game symbol. Please use either 'x' or 'o'", symbol));
        }
    }
}

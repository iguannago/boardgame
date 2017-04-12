package uk.co.boardgame.model;

import uk.co.boardgame.BusinessException;

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

package uk.co.boardgame.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class GameResult {

    @JsonProperty
    private Outcome outcome;

    public GameResult(Outcome outcome) {
        this.outcome = outcome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GameResult that = (GameResult) o;

        return new EqualsBuilder()
                .append(outcome, that.outcome)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(outcome)
                .toHashCode();
    }

    @Override
    public String toString() {
        return outcome == null ? "" : outcome.name();
    }
}

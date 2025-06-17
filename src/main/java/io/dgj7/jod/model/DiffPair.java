package io.dgj7.jod.model;

import lombok.Getter;

/**
 * Pair of objects diff'd.
 */
public class DiffPair<T> {
    @Getter
    private final T expected;
    @Getter
    private final T actual;

    /**
     * Create a new instance.
     */
    public DiffPair(final T pExpected, final T pActual) {
        this.expected = pExpected;
        this.actual = pActual;
    }
}

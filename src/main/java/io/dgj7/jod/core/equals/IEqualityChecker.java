package io.dgj7.jod.core.equals;

import io.dgj7.jod.config.DifferencerConfiguration;

/**
 * <p>
 * Check whether two objects are equal.
 * </p>
 */
public interface IEqualityChecker {
    /**
     * Check whether two objects are equal.
     */
    boolean check(final DifferencerConfiguration config, final Object expected, final Object actual);
}

package io.dgj7.jod.core.behavior.equals;

import io.dgj7.jod.model.config.DifferencerConfiguration;

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

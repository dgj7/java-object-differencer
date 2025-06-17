package io.dgj7.jod.xt.equals;

/**
 * <p>
 * Check whether two objects are equal.
 * </p>
 */
public interface IEqualityChecker {
    /**
     * Check whether two objects are equal.
     */
    boolean check(final Object expected, final Object actual);
}

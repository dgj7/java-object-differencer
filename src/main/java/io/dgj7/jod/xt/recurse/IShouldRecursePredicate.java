package io.dgj7.jod.xt.recurse;

import io.dgj7.jod.model.eq.EquatableThings;

/**
 * <p>
 * Determine if the application should recurse into the given object(s).
 * </p>
 */
public interface IShouldRecursePredicate {
    /**
     * Determine if the application should recurse into the given object(s).
     */
    <T> boolean test(final EquatableThings et, final T expected, final T actual);
}

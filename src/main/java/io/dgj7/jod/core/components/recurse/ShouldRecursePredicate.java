package io.dgj7.jod.core.components.recurse;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * <p>
 * {@link Predicate} to determine if the differencer should recurse
 * into the object graph, or not (and thus compare the values).
 * </p>
 */
public class ShouldRecursePredicate implements BiPredicate<Object, Object> {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(final Object expected, final Object actual) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}

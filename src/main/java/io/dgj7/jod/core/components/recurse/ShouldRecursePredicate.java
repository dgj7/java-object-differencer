package io.dgj7.jod.core.components.recurse;

import io.dgj7.jod.model.Metadata;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * <p>
 * {@link Predicate} to determine if the differencer should recurse
 * into the object graph, or not (and thus compare the values).
 * </p>
 */
public class ShouldRecursePredicate extends AbstractShouldRecursePredicate {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(final Object expected, final Object actual) {
        final Object object = Optional.ofNullable(expected).orElse(actual);
        final Metadata md = Metadata.from(object);

        return isDirectlyEquatable(md);
    }
}

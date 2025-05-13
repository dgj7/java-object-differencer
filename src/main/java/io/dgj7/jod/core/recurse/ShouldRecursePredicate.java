package io.dgj7.jod.core.recurse;

import io.dgj7.jod.model.Metadata;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * <p>
 * {@link Predicate} to determine if the differencer should recurse
 * into the object graph, or not (and thus compare the values).
 * </p>
 */
public class ShouldRecursePredicate implements BiPredicate<Object, Object> {
    protected static final List<String> DIRECTLY_EQUATABLE_PACKAGES = List.of(
            "java.lang",
            "java.util"
    );

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(final Object expected, final Object actual) {
        final Object object = Optional.ofNullable(expected).orElse(actual);
        final Metadata md = Metadata.from(object);

        final boolean notDirectlyEquatable = !DIRECTLY_EQUATABLE_PACKAGES.contains(md.getPackageName());

        return notDirectlyEquatable;
    }
}

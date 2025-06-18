package io.dgj7.jod.xt.recurse.impl;

import io.dgj7.jod.xt.recurse.IShouldRecursePredicate;
import io.dgj7.jod.model.eq.EquatableThings;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * <p>
 * {@link Predicate} to determine if the differencer should recurse
 * into the object graph, or not (and thus compare the values).
 * </p>
 */
public class DefaultShouldRecursePredicate implements IShouldRecursePredicate {
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> boolean test(final EquatableThings et, final T expected, final T actual) {
        final Optional<T> maybe = Optional.ofNullable(expected)
                .or(() -> Optional.ofNullable(actual));
        final String packageName = maybe.map(x -> x.getClass().getPackageName()).orElse("");
        final Class<?> clazz = maybe.map(x -> x.getClass()).orElse(null);

        final boolean notDirectlyEquatablePackage = !et.getDirectlyEquatablePackages().contains(packageName);
        final boolean notDirectlyEquatableClass = et.getDirectlyEquatableClasses()
                .stream()
                .noneMatch(cl -> cl.equals(clazz));

        return notDirectlyEquatablePackage && notDirectlyEquatableClass;
    }
}

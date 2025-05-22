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
    protected List<String> provideDirectlyEquatablePackages() {
        return List.of(
                "java.lang",
                "java.util",
                "java.math",
                "java.time",
                "java.sql"
        );
    }

    protected List<Class<?>> provideDirectlyEquatableClasses() {
        return List.of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(final Object expected, final Object actual) {
        final Object object = Optional.ofNullable(expected).orElse(actual);
        final Metadata md = Metadata.from(object);

        final boolean notDirectlyEquatablePackage = !provideDirectlyEquatablePackages().contains(md.getPackageName());
        final boolean notDirectlyEquatableClass = provideDirectlyEquatableClasses()
                .stream()
                .map(Metadata::from)
                .noneMatch(md::equals);

        return notDirectlyEquatablePackage && notDirectlyEquatableClass;
    }
}

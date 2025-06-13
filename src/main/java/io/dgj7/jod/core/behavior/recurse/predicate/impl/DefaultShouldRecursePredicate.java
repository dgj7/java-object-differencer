package io.dgj7.jod.core.behavior.recurse.predicate.impl;

import io.dgj7.jod.core.behavior.recurse.predicate.IShouldRecursePredicate;
import io.dgj7.jod.core.md.AbstractMetaData;
import io.dgj7.jod.core.md.IMetaDataFactory;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.config.EquatableThings;

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
    public boolean test(final DifferencerConfiguration config, final Object expected, final Object actual) {
        final Object object = Optional.ofNullable(expected).orElse(actual);

        final IMetaDataFactory<? extends AbstractMetaData> mdf = config.getMetaDataFactory();
        final EquatableThings et = config.getEquatableThings();

        final AbstractMetaData md = mdf.from(config, object);

        final boolean notDirectlyEquatablePackage = !et.getDirectlyEquatablePackages().contains(md.providePackageName());
        final boolean notDirectlyEquatableClass = et.getDirectlyEquatableClasses()
                .stream()
                .map(cl -> mdf.from(config, cl))
                .noneMatch(md::equals);

        return notDirectlyEquatablePackage && notDirectlyEquatableClass;
    }
}

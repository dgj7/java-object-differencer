package io.dgj7.jod.core.recurse.impl;

import io.dgj7.jod.core.md.AbstractMetaData;
import io.dgj7.jod.core.md.IMetaDataFactory;
import io.dgj7.jod.core.recurse.IShouldRecursePredicate;
import io.dgj7.jod.model.config.DifferencerConfiguration;

import java.util.List;
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
     * <p>
     * Provide a list of packages, within which, all objects are directly equatable.
     * </p>
     * <p>
     * "Directly equatable" means that an object:
     * <ul>
     *     <li>does not need to have it's object graph recursed into further, and</li>
     *     <li>it's equals() method can be called to determine if it's equal to another object of the same type</li>
     * </ul>
     * </p>
     */
    protected List<String> provideDirectlyEquatablePackages() {
        return List.of(
                "java.lang",
                "java.util",
                "java.math",
                "java.time",
                "java.sql"
        );
    }

    /**
     * <p>
     * Provide a list of classes that are directly equatable.
     * </p>
     * <p>
     * "Directly equatable" means that an object:
     * <ul>
     *     <li>does not need to have it's object graph recursed into further, and</li>
     *     <li>it's equals() method can be called to determine if it's equal to another object of the same type</li>
     * </ul>
     * </p>
     */
    protected List<Class<?>> provideDirectlyEquatableClasses() {
        return List.of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(final DifferencerConfiguration config, final Object expected, final Object actual) {
        final Object object = Optional.ofNullable(expected).orElse(actual);

        final IMetaDataFactory<? extends AbstractMetaData> mdf = config.getMetaDataFactory();

        final AbstractMetaData md = mdf.from(object);

        final boolean notDirectlyEquatablePackage = !provideDirectlyEquatablePackages().contains(md.providePackageName());
        final boolean notDirectlyEquatableClass = provideDirectlyEquatableClasses()
                .stream()
                .map(mdf::from)
                .noneMatch(md::equals);

        return notDirectlyEquatablePackage && notDirectlyEquatableClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> boolean checkSuperTypeFields(final DifferencerConfiguration config, final Class<T> clazz) {
        return !provideDirectlyEquatablePackages().contains(clazz.getPackageName());
    }
}

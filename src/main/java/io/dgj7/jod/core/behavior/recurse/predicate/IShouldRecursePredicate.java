package io.dgj7.jod.core.behavior.recurse.predicate;

import io.dgj7.jod.model.config.DifferencerConfiguration;

/**
 * <p>
 * Determine if the application should recurse into the given object(s).
 * </p>
 */
public interface IShouldRecursePredicate {
    /**
     * Determine if the application should recurse into the given object(s).
     */
    boolean test(final DifferencerConfiguration config, final Object expected, final Object actual);

    /**
     * Determine if fields from the supertype should be gathered.
     */
    <T> boolean checkSuperTypeFields(final DifferencerConfiguration config, final Class<T> clazz);
}

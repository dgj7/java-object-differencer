package io.dgj7.jod.core.collections;

import java.util.Collection;

/**
 * <p>
 * Handle collections.
 * </p>
 */
public interface ICollectionHandler {
    /**
     * Determine if the given object is a collection.
     */
    <I> boolean isCollection(final I expected, final I actual);

    /**
     * Get elements in the collection.
     */
    <I, O> Collection<O> findAllElements(final I object);
}

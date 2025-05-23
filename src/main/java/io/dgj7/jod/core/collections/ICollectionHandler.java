package io.dgj7.jod.core.collections;

import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;

import java.util.Collection;
import java.util.List;

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
    <I, O> Collection<O> findCollection(final I object);

    /**
     * Diff two collections.
     */
    <T> void diffCollections(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final Collection<T> expectedList, final Collection<T> actualList);
}

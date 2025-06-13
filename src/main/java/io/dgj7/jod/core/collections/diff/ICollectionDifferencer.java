package io.dgj7.jod.core.collections.diff;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * Handle collections.
 * </p>
 */
public interface ICollectionDifferencer {
    /**
     * Diff two collections.
     */
    <T> void diffCollections(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final Collection<T> expectedList, final Collection<T> actualList);
}

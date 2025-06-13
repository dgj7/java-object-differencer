package io.dgj7.jod.core.collections.diff.impl;

import io.dgj7.jod.core.collections.diff.ICollectionDifferencer;
import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * Default {@link ICollectionDifferencer}.
 * </p>
 * <p>
 * Compares elements in both collections, in order.  Does NOT attempt
 * to find the most likely match or compare base/abstract types.
 * </p>
 */
public class DefaultCollectionDifferencer implements ICollectionDifferencer {
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void diffCollections(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final Collection<T> expectedList, final Collection<T> actualList) {
        final IObjectDifferencer od = config.getObjectDifferencer();

        if (expectedList.size() != actualList.size()) {
            deltas.add(Delta.from(config, DeltaType.COLLECTION_SIZES_NOT_EQUAL, prefixPath, expectedList.size(), actualList.size()));
        }

        final Iterator<T> expectedIterator = expectedList.iterator();
        final Iterator<T> actualIterator = actualList.iterator();
        int c = 0;

        while (expectedIterator.hasNext()) {
            final T expected = expectedIterator.next();
            final String path = prefixPath + "[" + c++ + "]";

            if (actualIterator.hasNext()) {
                final T actual = actualIterator.next();

                od.diffObjects(config, deltas, path, expected, actual);
            } else {
                deltas.add(Delta.noMatchingElement(config, path, expected));
            }
        }
    }
}

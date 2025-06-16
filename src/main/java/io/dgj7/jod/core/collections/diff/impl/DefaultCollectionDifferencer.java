package io.dgj7.jod.core.collections.diff.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.collections.diff.ICollectionDifferencer;
import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.util.ArrayList;
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
        /* pull behaviors */
        final IObjectDifferencer od = config.getObjectDifferencer();

        /* add delta if collections are different sizes */
        if (expectedList.size() != actualList.size()) {
            deltas.add(Delta.from(config, DeltaType.COLLECTION_SIZES_NOT_EQUAL, prefixPath, expectedList.size(), actualList.size()));
        }

        /* get "iterators" */
        final List<T> actualListCopy = new ArrayList<>(actualList);
        int c = 0;

        /* attempt to find a match for each element in the expected collection */
        for (T expected : expectedList) {
            final String path = prefixPath + "[" + c++ + "]";
            if (expected == null) {
                int index = -1;
                for (int d = 0; d < actualListCopy.size(); d++) {
                    if (actualListCopy.get(d) == null) {
                        index = d;
                    }
                }
                if (index >= 0) {
                    actualListCopy.remove(index);
                } else {
                    deltas.add(Delta.noMatchingElement(config, path, "null"));
                }
            } else {
                if (actualListCopy.isEmpty()) {
                    deltas.add(Delta.noMatchingElement(config, path, expected));
                } else {
                    final T actual = actualListCopy.get(0);
                    od.diffObjects(config, deltas, path, expected, actual);
                    actualListCopy.remove(actual);
                }
            }
        }

        /* add a diff for any 'extra' elements in the actual list */
        for (int e = 0; e < actualListCopy.size(); e++) {
            final T extra = actualListCopy.get(e);
            deltas.add(Delta.extraElement(config, prefixPath + "[" + c + "+" + (e + 1) + "]", extra));
        }
    }
}

package io.dgj7.jod.core.collections.impl;

import io.dgj7.jod.core.collections.ICollectionHandler;
import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * Default {@link ICollectionHandler}.
 * </p>
 */
public class DefaultCollectionHandler implements ICollectionHandler {
    /**
     * {@inheritDoc}
     */

    @Override
    public <I> boolean isCollection(final I expected, final I actual) {
        return Stream.of(expected, actual)
                .filter(Objects::nonNull)
                .map(Object::getClass)
                .allMatch(Collection.class::isAssignableFrom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <I, O> Collection<O> findCollection(final I object) {
        return (Collection<O>) object;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void diffCollections(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final Collection<T> expectedList, final Collection<T> actualList) {
        final IObjectDifferencer od = config.getObjectDifferencer();

        if (expectedList.size() == actualList.size()) {
            final Iterator<T> expectedIterator = expectedList.iterator();
            final Iterator<T> actualIterator = actualList.iterator();
            int c = 0;

            while (expectedIterator.hasNext()) {
                final T expected = expectedIterator.next();
                final T actual = actualIterator.next();
                final String path = prefixPath + "[" + c++ + "]";
                od.diffObjects(config, deltas, path, expected, actual);
            }
        } else {
            deltas.add(Delta.from(config, DeltaType.COLLECTION_SIZES_NOT_EQUAL, prefixPath, expectedList.size(), actualList.size()));
        }
    }
}

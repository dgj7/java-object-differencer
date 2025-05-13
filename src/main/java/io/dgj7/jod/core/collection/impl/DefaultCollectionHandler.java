package io.dgj7.jod.core.collection.impl;

import io.dgj7.jod.core.collection.ICollectionHandler;

import java.util.Collection;
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
    public boolean isCollection(final Object expected, final Object actual) {
        return Stream.of(expected, actual)
                .filter(Objects::nonNull)
                .map(Object::getClass)
                .anyMatch(Collection.class::isAssignableFrom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Object> findAllElements(final Object object) {
        return (List<Object>) object;
    }
}

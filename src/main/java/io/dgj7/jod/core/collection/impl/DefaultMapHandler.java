package io.dgj7.jod.core.collection.impl;

import io.dgj7.jod.core.collection.IMapHandler;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Default {@link IMapHandler}.
 */
public class DefaultMapHandler implements IMapHandler {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMap(final Object expected, final Object actual) {
        return Stream.of(expected, actual)
                .filter(Objects::nonNull)
                .map(Object::getClass)
                .anyMatch(Map.class::isAssignableFrom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <K, V> Map<K, V> findAllElements(final Object object) {
        return (Map<K, V>) object;
    }
}

package io.dgj7.jod.core.components.collection.impl;

import io.dgj7.jod.core.components.collection.IMapHandler;

import java.util.Map;

/**
 * Default {@link IMapHandler}.
 */
public class DefaultMapHandler implements IMapHandler {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMap(final Object expected, final Object actual) {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V> Map<K, V> findAllElements(Object object) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}

package io.dgj7.jod.core.components.collection.impl;

import io.dgj7.jod.core.components.collection.IMapHandler;

import java.util.List;
import java.util.Map;

/**
 * Default {@link IMapHandler}.
 */
public class DefaultMapHandler implements IMapHandler {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMap() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K, V> List<Map.Entry<K, V>> findAllElements(Object object) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}

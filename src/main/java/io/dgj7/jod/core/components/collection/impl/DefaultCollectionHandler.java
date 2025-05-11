package io.dgj7.jod.core.components.collection.impl;

import io.dgj7.jod.core.components.collection.ICollectionHandler;

import java.util.List;

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
    public boolean isCollection() {
        throw new UnsupportedOperationException("not yet implemented");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object> findAllElements(Object object) {
        throw new UnsupportedOperationException("not yet implemented");
    }
}

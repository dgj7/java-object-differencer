package io.dgj7.jod.xt.collections.transform.impl;

import io.dgj7.jod.xt.collections.transform.ICollectionTransformer;

import java.util.Collection;

/**
 * <p>
 * Default {@link ICollectionTransformer}.
 * </p>
 */
public class DefaultCollectionTransformer implements ICollectionTransformer {
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <I, O> Collection<O> objectToCollection(final I object) {
        return (Collection<O>) object;
    }
}

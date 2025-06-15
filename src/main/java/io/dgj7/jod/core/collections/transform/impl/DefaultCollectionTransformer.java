package io.dgj7.jod.core.collections.transform.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.collections.transform.ICollectionTransformer;

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
    public <I, O> Collection<O> objectToCollection(final DifferencerConfiguration config, final I object) {
        return (Collection<O>) object;
    }
}

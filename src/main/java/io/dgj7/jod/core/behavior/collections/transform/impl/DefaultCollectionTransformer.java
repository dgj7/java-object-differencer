package io.dgj7.jod.core.behavior.collections.transform.impl;

import io.dgj7.jod.core.behavior.collections.transform.ICollectionTransformer;
import io.dgj7.jod.model.config.DifferencerConfiguration;

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

package io.dgj7.jod.core.behavior.maps.transform.impl;

import io.dgj7.jod.core.behavior.maps.transform.IMapTransformer;
import io.dgj7.jod.model.config.DifferencerConfiguration;

import java.util.Map;

/**
 * <p>
 * Default {@link IMapTransformer}.
 * </p>
 */
public class DefaultMapTransformer implements IMapTransformer {
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <K, V> Map<K, V> objectToMap(final DifferencerConfiguration config, final Object object) {
        return (Map<K, V>) object;
    }
}

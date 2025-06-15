package io.dgj7.jod.core.maps.transform.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.maps.transform.IMapTransformer;

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

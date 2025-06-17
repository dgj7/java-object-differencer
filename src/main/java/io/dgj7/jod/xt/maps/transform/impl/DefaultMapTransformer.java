package io.dgj7.jod.xt.maps.transform.impl;

import io.dgj7.jod.xt.maps.transform.IMapTransformer;

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
    public <K, V> Map<K, V> objectToMap(final Object object) {
        return (Map<K, V>) object;
    }
}

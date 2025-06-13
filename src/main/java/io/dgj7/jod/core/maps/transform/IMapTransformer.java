package io.dgj7.jod.core.maps.transform;

import io.dgj7.jod.config.DifferencerConfiguration;

import java.util.Map;

/**
 * <p>
 * Transform a given object into a {@link Map}.
 * </p>
 */
public interface IMapTransformer {
    /**
     * Get elements in the map.
     */
    <K, V> Map<K, V> objectToMap(final DifferencerConfiguration config, final Object object);
}

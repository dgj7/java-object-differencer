package io.dgj7.jod.core.behavior.maps.transform;

import io.dgj7.jod.model.config.DifferencerConfiguration;

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

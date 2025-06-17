package io.dgj7.jod.xt.maps.transform;

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
    <K, V> Map<K, V> objectToMap(final Object object);
}

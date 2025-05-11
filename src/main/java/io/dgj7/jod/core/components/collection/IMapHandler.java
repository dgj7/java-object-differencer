package io.dgj7.jod.core.components.collection;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Handle maps.
 * </p>
 */
public interface IMapHandler {
    /**
     * Determine if the given object is a list.
     */
    boolean isMap();

    /**
     * Get elements in the collection.
     */
    <K, V> List<Map.Entry<K, V>> findAllElements(final Object object);
}

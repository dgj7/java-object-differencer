package io.dgj7.jod.core.collection;

import java.util.Map;

/**
 * <p>
 * Handle maps.
 * </p>
 */
public interface IMapHandler {
    /**
     * Determine if the given object is a map.
     */
    boolean isMap(final Object expected, final Object actual);

    /**
     * Get elements in the map.
     */
    <K, V> Map<K, V> findAllElements(final Object object);
}

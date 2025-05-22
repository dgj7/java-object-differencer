package io.dgj7.jod.core.maps;

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
    <I> boolean isMap(final I expected, final I actual);

    /**
     * Get elements in the map.
     */
    <K, V> Map<K, V> findAllElements(final Object object);
}

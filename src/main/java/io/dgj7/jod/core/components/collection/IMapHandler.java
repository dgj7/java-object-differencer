package io.dgj7.jod.core.components.collection;

import java.util.Map;

/**
 * <p>
 * Handle maps.
 * </p>
 */
public interface IMapHandler {
    /**
     * Determine if the given object is a collection.
     */
    boolean isMap(final Object expected, final Object actual);

    /**
     * Get elements in the collection.
     */
    <K, V> Map<K, V> findAllElements(final Object object);
}

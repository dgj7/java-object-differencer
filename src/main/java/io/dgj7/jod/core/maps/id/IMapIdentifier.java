package io.dgj7.jod.core.maps.id;

import java.util.Map;

/**
 * <p>
 * Determine if a given object is a {@link Map}.
 * </p>
 */
public interface IMapIdentifier {
    /**
     * Determine if the given object is a map.
     */
    <I> boolean isMap(final I expected, final I actual);
}

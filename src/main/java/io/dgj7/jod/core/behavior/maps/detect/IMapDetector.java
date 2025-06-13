package io.dgj7.jod.core.behavior.maps.detect;

import java.util.Map;

/**
 * <p>
 * Determine if a given object is a {@link Map}.
 * </p>
 */
public interface IMapDetector {
    /**
     * Determine if the given object is a map.
     */
    // todo: add differencer configuration parameter
    <I> boolean isMap(final I expected, final I actual);
}

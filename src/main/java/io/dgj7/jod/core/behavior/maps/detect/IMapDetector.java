package io.dgj7.jod.core.behavior.maps.detect;

import io.dgj7.jod.model.config.DifferencerConfiguration;

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
    <I> boolean isMap(final DifferencerConfiguration config, final I expected, final I actual);
}

package io.dgj7.jod.core.enumerations;

import io.dgj7.jod.config.DifferencerConfiguration;

/**
 * <p>
 * Detect enumerations.
 * </p>
 */
public interface IEnumDetector {
    /**
     * Determine if enumeration.
     */
    boolean isEnum(final DifferencerConfiguration config, final Object expected, final Object actual);
}

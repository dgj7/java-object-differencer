package io.dgj7.jod.core.behavior.enumerations;

import io.dgj7.jod.model.config.DifferencerConfiguration;

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

package io.dgj7.jod.core.behavior.enumerations;

/**
 * <p>
 * Detect enumerations.
 * </p>
 */
public interface IEnumDetector {
    /**
     * Determine if enumeration.
     */
    // todo: add differencer configuration parameter
    boolean isEnum(final Object expected, final Object actual);
}

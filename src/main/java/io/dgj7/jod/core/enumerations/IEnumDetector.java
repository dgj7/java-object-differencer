package io.dgj7.jod.core.enumerations;

/**
 * <p>
 * Detect enumerations.
 * </p>
 */
public interface IEnumDetector {
    /**
     * Determine if enumeration.
     */
    boolean isEnum(final Object expected, final Object actual);
}

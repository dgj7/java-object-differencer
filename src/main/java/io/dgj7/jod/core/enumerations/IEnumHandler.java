package io.dgj7.jod.core.enumerations;

/**
 * <p>
 * Handle enumerations.
 * </p>
 */
public interface IEnumHandler {
    /**
     * Determine if enumeration.
     */
    boolean isEnum(final Object expected, final Object actual);
}

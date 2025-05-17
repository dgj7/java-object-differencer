package io.dgj7.jod.core.collection.impl;

import io.dgj7.jod.core.collection.IEnumHandler;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * Handle enumerations.
 * </p>
 */
public class DefaultEnumHandler implements IEnumHandler {
    /**
     * Determine if enumeration.
     */
    @Override
    public boolean isEnum(final Object expected, final Object actual) {
        return Stream.of(expected, actual)
                .filter(Objects::nonNull)
                .map(Object::getClass)
                .anyMatch(Enum.class::isAssignableFrom);
    }
}

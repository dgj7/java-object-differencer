package io.dgj7.jod.core.behavior.enumerations.impl;

import io.dgj7.jod.core.behavior.enumerations.IEnumDetector;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * Default {@link IEnumDetector}.
 * </p>
 */
public class DefaultEnumDetector implements IEnumDetector {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnum(final Object expected, final Object actual) {
        return Stream.of(expected, actual)
                .filter(Objects::nonNull)
                .map(Object::getClass)
                .allMatch(Enum.class::isAssignableFrom);
    }
}

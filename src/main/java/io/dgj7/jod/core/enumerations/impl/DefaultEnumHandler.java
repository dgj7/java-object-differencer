package io.dgj7.jod.core.enumerations.impl;

import io.dgj7.jod.core.enumerations.IEnumHandler;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * Default {@link IEnumHandler}.
 * </p>
 */
public class DefaultEnumHandler implements IEnumHandler {
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

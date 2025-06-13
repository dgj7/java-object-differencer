package io.dgj7.jod.core.maps.id.impl;

import io.dgj7.jod.core.maps.id.IMapIdentifier;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * Default {@link IMapIdentifier}.
 * </p>
 */
public class DefaultMapIdentifier implements IMapIdentifier {
    /**
     * {@inheritDoc}
     */
    @Override
    public <I> boolean isMap(final I expected, final I actual) {
        return Stream.of(expected, actual)
                .filter(Objects::nonNull)
                .map(Object::getClass)
                .allMatch(Map.class::isAssignableFrom);
    }
}

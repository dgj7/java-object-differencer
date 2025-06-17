package io.dgj7.jod.xt.maps.detect.impl;

import io.dgj7.jod.xt.maps.detect.IMapDetector;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * Default {@link IMapDetector}.
 * </p>
 */
public class DefaultMapDetector implements IMapDetector {
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

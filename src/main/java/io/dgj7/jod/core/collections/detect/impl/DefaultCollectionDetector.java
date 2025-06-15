package io.dgj7.jod.core.collections.detect.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.collections.detect.ICollectionDetector;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * Default {@link ICollectionDetector}.
 * </p>
 */
public class DefaultCollectionDetector implements ICollectionDetector {
    /**
     * {@inheritDoc}
     */
    @Override
    public <I> boolean isCollection(final DifferencerConfiguration config, final I expected, final I actual) {
        final List<? extends Class<?>> matches = Stream.of(expected, actual)
                .filter(Objects::nonNull)
                .map(Object::getClass)
                .filter(Collection.class::isAssignableFrom)
                .toList();
        return !matches.isEmpty();
    }
}

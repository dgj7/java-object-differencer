package io.dgj7.jod.core.collections.id.impl;

import io.dgj7.jod.core.collections.id.ICollectionDetector;

import java.util.Collection;
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
    public <I> boolean isCollection(final I expected, final I actual) {
        return Stream.of(expected, actual)
                .filter(Objects::nonNull)
                .map(Object::getClass)
                .allMatch(Collection.class::isAssignableFrom);
    }
}

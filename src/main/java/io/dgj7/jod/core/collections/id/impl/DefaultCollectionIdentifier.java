package io.dgj7.jod.core.collections.id.impl;

import io.dgj7.jod.core.collections.id.ICollectionIdentifier;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>
 * Default {@link ICollectionIdentifier}.
 * </p>
 */
public class DefaultCollectionIdentifier implements ICollectionIdentifier {
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

package io.dgj7.jod.core.maps;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Default {@link IMapHandler}.
 */
public class DefaultMapHandler implements IMapHandler {
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

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <K, V> Map<K, V> findAllElements(final Object object) {
        return (Map<K, V>) object;
    }
}

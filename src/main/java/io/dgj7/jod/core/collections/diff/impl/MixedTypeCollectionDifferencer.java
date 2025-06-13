package io.dgj7.jod.core.collections.diff.impl;

import io.dgj7.jod.core.collections.diff.ICollectionDifferencer;
import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;

import java.util.*;
import java.util.function.BiPredicate;

/**
 * <p>
 * {@link ICollectionDifferencer} that overrides {@link ICollectionDifferencer#diffCollections(DifferencerConfiguration, List, String, Collection, Collection)}
 * on {@link DefaultCollectionDifferencer} to allow for lists that contain mixed types, or mixed
 * list indices.
 * </p>
 */
public class MixedTypeCollectionDifferencer extends DefaultCollectionDifferencer {
    private final Map<Class<?>, BiPredicate<?, ?>> equalityStrategies;
    private final BiPredicate<?, ?> defaultEqualityStrategy;

    /**
     * Create a new instance.
     */
    public MixedTypeCollectionDifferencer(final Map<Class<?>, BiPredicate<?, ?>> pEqualityStrategies, final BiPredicate<?, ?> pDefaultEqualityStrategy) {
        this.equalityStrategies = Objects.requireNonNull(pEqualityStrategies, "Map<Class<?>, BiPredicate<?, ?>> is null");
        this.defaultEqualityStrategy = Objects.requireNonNull(pDefaultEqualityStrategy, "BiPredicate<?, ?> is null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void diffCollections(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final Collection<T> expectedList, final Collection<T> actualList) {
        final IObjectDifferencer od = config.getObjectDifferencer();
        final List<T> actualListCopy = new ArrayList<>(actualList);
        int c = 0;
        for (T expectedElement : expectedList) {
            if (expectedElement == null) {
                int index = -1;
                for (int d = 0; d < actualListCopy.size(); d++) {
                    if (actualListCopy.get(d) == null) {
                        index = d;
                    }
                }
                if (index >= 0) {
                    actualListCopy.remove(index);
                } else {
                    final String path = prefixPath + "[" + c++ + "]";
                    deltas.add(Delta.noMatchingElement(config, path, "null"));
                }
            } else {
                final List<T> actualListPotentials = actualListCopy.stream()
                        .filter(Objects::nonNull)
                        .filter(al -> expectedElement.getClass().equals(al.getClass()))
                        .toList();
                final Optional<T> maybeActualElement = findActualElement(expectedElement, actualListPotentials);
                final String path = prefixPath + "[" + c++ + "]";
                if (maybeActualElement.isPresent()) {
                    final T actualElement = maybeActualElement.get();
                    actualListCopy.remove(actualElement);
                    od.diffObjects(config, deltas, path, expectedElement, actualElement);
                } else {
                    deltas.add(Delta.noMatchingElement(config, path, expectedElement));
                }
            }
        }
    }

    /**
     * Find an "actual" element in the actual list to compare to.
     */
    private <T> Optional<T> findActualElement(final T expectedElement, final List<T> actualListCopy) {
        for (T actualElement : actualListCopy) {
            final BiPredicate<T, T> equalityStrategy = findEqualityStrategy(expectedElement, actualElement);
            if (equalityStrategy.test(expectedElement, actualElement)) {
                return Optional.of(actualElement);
            }
        }
        return Optional.empty();
    }

    /**
     * Determine a match-finding equality strategy.
     */
    @SuppressWarnings("unchecked")
    private <T> BiPredicate<T, T> findEqualityStrategy(final T expected, final T actual) {
        final Class<T> clazz;
        if (expected != null) {
            clazz = (Class<T>) expected.getClass();
        } else {
            clazz = actual != null ? (Class<T>) actual.getClass() : null;
        }
        return (BiPredicate<T, T>) equalityStrategies.getOrDefault(clazz, defaultEqualityStrategy);
    }
}

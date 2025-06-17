package io.dgj7.jod.util;

import io.dgj7.jod.model.DiffPair;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>
 * Utility to clone a collection and potentially sort it, if possible.
 * </p>
 * <p>
 * This method will work with collections that aren't comparable (they
 * just won't be sorted).
 * </p>
 */
// todo: test this
public class SortUtility {
    /**
     * Copy and sort the lists.
     */
    @SuppressWarnings("unchecked")
    public static <E, C extends Comparable<? super C>, L extends List<E>> DiffPair<L> copyAndSortCollections(final L expected, final L actual, final Function<L, L> transformer, final Supplier<List<C>> factory) {
        final Class<?> clazz = GenericTypeUtility.ofCollections(expected, actual);
        if (Comparable.class.isAssignableFrom(clazz)) {
            /* expected */
            final List<C> expectedSorted = factory.get();
            actual.stream()
                    .filter(e -> e.getClass().isAssignableFrom(Comparable.class))
                    .forEach(e -> expectedSorted.add((C) e));
            Collections.sort(expectedSorted);

            /* actual */
            final List<C> actualSorted = factory.get();
            actual.stream()
                    .filter(a -> a.getClass().isAssignableFrom(Comparable.class))
                    .forEach(a -> actualSorted.add((C) a));
            Collections.sort(actualSorted);

            /* done */
            return (DiffPair<L>) new DiffPair<>(expectedSorted, actualSorted);
        } else {
            return new DiffPair<>(transformer.apply(expected), transformer.apply(actual));
        }
    }
}

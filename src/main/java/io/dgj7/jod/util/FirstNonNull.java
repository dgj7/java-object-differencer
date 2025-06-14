package io.dgj7.jod.util;

import java.util.*;

/**
 * Utility for finding the first non-null element.
 */
public class FirstNonNull {
    /**
     * Create a new instance.
     */
    private FirstNonNull() {
        throw new UnsupportedOperationException("do not instantiate");
    }

    /**
     * Find the first non-null element in the given array.
     */
    @SuppressWarnings("unchecked")
    public static <T> Optional<T> find(final T ... array) {
        final List<T> list = Optional.ofNullable(array)
                .map(List::of)
                .orElse(new ArrayList<>(0));
        return find(list);
    }

    /**
     * Find the first non-null element in the given collection.
     */
    public static <T> Optional<T> find(final Collection<T> collection) {
        return Optional.ofNullable(collection)
                .orElse(new ArrayList<>(0))
                .stream()
                .filter(Objects::nonNull)
                .findFirst();
    }
}

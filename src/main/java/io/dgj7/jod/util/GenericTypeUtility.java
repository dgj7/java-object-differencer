package io.dgj7.jod.util;

import java.util.*;

/**
 * <p>
 * Utility for detection of generic type parameters.
 * </p>
 */
public class GenericTypeUtility {
    /**
     * <p>
     * Determine the generic type of collection.
     * </p>
     * <p>
     * It's impossible to guarantee the actual declared generic type, due to
     * type erasure.  Instead, this method will attempt to find a <i>common</i>
     * type amongst those in the list, and return that class.
     * </p>
     * <p>
     * Returns {@link Object}.class for empty lists, or for failing to find a class.
     * </p>
     */
    public static <T> Class<?> ofCollections(final Collection<T> expected, final Collection<T> actual) {
        final Set<Class<?>> classes = findClasses(expected, actual);
        if (classes.isEmpty()) {
            return Object.class;
        } else if (classes.size() == 1) {
            return classes.stream()
                    .findFirst()
                    .orElse(Object.class);
        } else {
            return findCommonBaseClass(classes)
                    .orElse(Object.class);
        }
    }

    /**
     * Find classes within the given collections.
     */
    private static <T> Set<Class<?>> findClasses(final Collection<T> expected, final Collection<T> actual) {
        final Set<Class<?>> classes = new HashSet<>();
        classes.addAll(findClasses(expected));
        classes.addAll(findClasses(actual));
        return classes;
    }

    /**
     * Find classes within the given collection.
     */
    private static <T> Set<Class<?>> findClasses(final Collection<T> collection) {
        final Set<Class<?>> classes = new HashSet<>();
        Optional.ofNullable(collection)
                .orElse(new ArrayList<>())
                .stream()
                .filter(Objects::nonNull)
                .map(elem -> elem.getClass())
                .forEach(classes::add);
        return classes;
    }

    /**
     * Find a common base class, if one exists.
     */
    private static Optional<Class<?>> findCommonBaseClass(final Set<Class<?>> classes) {
        for (Class<?> potential : classes) {
            Class<?> parent = potential.getSuperclass();
            while (parent != Object.class) {
                if (isCommonBase(classes, parent)) {
                    return Optional.ofNullable(parent);
                }
                parent = parent.getSuperclass();
            }
        }
        return Optional.empty();
    }

    /**
     * Determine if the given class is a base for the other classes.
     */
    private static boolean isCommonBase(final Set<Class<?>> classes, final Class<?> potential) {
        for (Class<?> other : classes) {
            if (!potential.isAssignableFrom(other)) {
                return false;
            }
        }
        return true;
    }
}

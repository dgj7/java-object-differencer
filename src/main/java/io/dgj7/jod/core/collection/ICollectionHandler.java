package io.dgj7.jod.core.collection;

import java.util.List;

/**
 * <p>
 * Handle collections.
 * </p>
 * <p>
 * Note that this object returns a list, which maintains ordering.
 * It is the responsibility of this implementation to return the elements
 * in an order that can be compared by index.  If the lists are equal but
 * out of order, the comparison will fail.
 * </p>
 */
public interface ICollectionHandler {
    /**
     * Determine if the given object is a collection.
     */
    boolean isCollection(final Object expected, final Object actual);

    /**
     * Get elements in the collection.
     */
    List<Object> findAllElements(final Object object);
}

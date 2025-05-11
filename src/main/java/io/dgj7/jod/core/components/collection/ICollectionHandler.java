package io.dgj7.jod.core.components.collection;

import java.util.List;

/**
 * <p>
 * Handle collections.
 * </p>
 */
public interface ICollectionHandler {
    /**
     * Determine if the given object is a list.
     */
    boolean isCollection();

    /**
     * Get elements in the collection.
     */
    List<Object> findAllElements(final Object object);
}

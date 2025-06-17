package io.dgj7.jod.xt.collections.transform;

import java.util.Collection;

/**
 * <p>
 * Transform an object into a {@link java.util.Collection}.
 * </p>
 */
public interface ICollectionTransformer {
    /**
     * Get elements in the collection.
     */
    <I, O> Collection<O> objectToCollection(final I object);
}

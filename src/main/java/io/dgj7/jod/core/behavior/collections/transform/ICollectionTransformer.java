package io.dgj7.jod.core.behavior.collections.transform;

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
    // todo: add differencer configuration parameter
    <I, O> Collection<O> objectToCollection(final I object);
}

package io.dgj7.jod.core.collections.id;

/**
 * <p>
 * Determine if an object is a {@link java.util.Collection}.
 * </p>
 * <p>
 * This object is expected to be reused.  That is to say, only a single
 * instance of it needs to exist for all of your collection-identification
 * needs.
 * </p>
 */
public interface ICollectionIdentifier {
    /**
     * Determine if the given object is a collection.
     */
    <I> boolean isCollection(final I expected, final I actual);
}

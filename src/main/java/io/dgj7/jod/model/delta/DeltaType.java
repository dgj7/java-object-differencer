package io.dgj7.jod.model.delta;

/**
 * <p>
 * Ways that two objects or fields can differ.
 * </p>
 */
public enum DeltaType {
    DIFFERENT_TYPES,
    NULLITY,
    NOT_EQUAL,
    COLLECTION_SIZES_NOT_EQUAL,
    MAP_SIZES_NOT_EQUAL,
    ;
}

package io.dgj7.jod.core.behavior.reflect.transform;

import java.lang.reflect.Field;

/**
 * <p>
 * Implementation of reflection for the library.
 * </p>
 * <p>
 * The goal here is to enable clients to implement
 * their own reflection methods, if desired.
 * </p>
 */
public interface IFieldTransformer {
    /**
     * Map field on input I to Object.
     */
    <I> Object fieldToObject(final Field field, final I input);
}

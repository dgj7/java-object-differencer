package io.dgj7.jod.xt.reflect;

import io.dgj7.jod.model.eq.EquatableThings;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Methods for finding fields, via reflection.
 * </p>
 */
public interface IFieldFinder {
    /**
     * List all {@link Field}s.
     */
    <I> List<Field> findFields(final EquatableThings et, final I input);

    /**
     * Find field with the given name.
     */
    <I> Optional<Field> findField(final EquatableThings et, final I input, final String name);

    /**
     * Map field on input I to Object.
     */
    <I> Object fieldToObject(final Field field, final I input);
}

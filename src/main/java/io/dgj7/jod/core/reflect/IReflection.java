package io.dgj7.jod.core.reflect;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Implementation of reflection for the library.
 * </p>
 * <p>
 * The goal here is to enable clients to implement
 * their own reflection methods, if desired.
 * </p>
 */
public interface IReflection {
    /**
     * List all {@link Field}s.
     */
    <I> List<Field> fields(final I input);

    /**
     * Find field with the given name.
     */
    <I> Optional<Field> findField(final I input, final String name);

    /**
     * Map field on input I to Object.
     */
    <I> Object fieldTo(final Field field, final I input);
}

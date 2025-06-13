package io.dgj7.jod.core.reflect.impl;

import io.dgj7.jod.core.reflect.IReflection;
import io.dgj7.jod.model.exc.ReflectionException;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * <p>
 * Default IReflect} implementation for the library.
 * </p>
 */
public class DefaultReflectionImpl implements IReflection {
    /**
     * {@inheritDoc}
     */
    @Override
    public <I> Object fieldTo(final Field pField, final I input) {
        /* null check */
        if (input == null) {
            return null;
        }

        /* preconditions */
        final Field field = Objects.requireNonNull(pField, "Field is null");

        /* modify field */
        field.setAccessible(true);

        /* pull the value */
        try {
            return field.get(input);
        } catch (IllegalAccessException iaex) {
            throw new ReflectionException(iaex);
        }
    }
}

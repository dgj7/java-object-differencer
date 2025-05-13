package io.dgj7.jod.core.reflect.impl;

import io.dgj7.jod.core.reflect.IReflection;
import io.dgj7.jod.model.exc.ReflectionException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public <I> List<Field> fields(final I pInput) {
        final I input = Objects.requireNonNull(pInput, "Input(I) is null");

        return Arrays.stream(input.getClass().getDeclaredFields()).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <I> Optional<Field> findField(final I pInput, final String pName) {
        final I input = Objects.requireNonNull(pInput, "Input(I) is null");
        final String name = Objects.requireNonNull(pName, "Name(String) is null");

        return fields(input)
                .stream()
                .filter(f -> name.equals(f.getName()))
                .findFirst();
    }

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

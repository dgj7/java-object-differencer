package io.dgj7.jod.core.reflect.impl;

import io.dgj7.jod.core.recurse.IShouldRecursePredicate;
import io.dgj7.jod.core.reflect.IReflection;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.exc.ReflectionException;

import java.lang.reflect.Field;
import java.util.*;

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
    public <I> List<Field> fields(final DifferencerConfiguration config, final I pInput) {
        final I input = Objects.requireNonNull(pInput, "Input(I) is null");
        final IShouldRecursePredicate srp = config.getShouldRecurse();

        final List<Field> fields = new LinkedList<>();
        Class<?> clazz = input.getClass();

        while (srp.checkSuperTypeFields(config, clazz)) {
            fields.addAll(Arrays.stream(clazz.getDeclaredFields()).toList());
            clazz = clazz.getSuperclass();
        }

        return fields;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <I> Optional<Field> findField(final DifferencerConfiguration config, final I pInput, final String pName) {
        final I input = Objects.requireNonNull(pInput, "Input(I) is null");
        final String name = Objects.requireNonNull(pName, "Name(String) is null");

        return fields(config, input)
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

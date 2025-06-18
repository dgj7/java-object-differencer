package io.dgj7.jod.xt.reflect.impl;

import io.dgj7.jod.model.eq.EquatableThings;
import io.dgj7.jod.model.exc.ReflectionException;
import io.dgj7.jod.xt.reflect.IFieldFinder;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Default {@link IFieldFinder}.
 */
public class DefaultFieldFinder implements IFieldFinder {
    /**
     * {@inheritDoc}
     */
    @Override
    public <I> Optional<Field> findField(final EquatableThings et, final I pInput, final String pName) {
        final I input = Objects.requireNonNull(pInput, "Input(I) is null");
        final String name = Objects.requireNonNull(pName, "Name(String) is null");

        return findFields(et, input)
                .stream()
                .filter(f -> name.equals(f.getName()))
                .findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <I> List<Field> findFields(final EquatableThings et, final I pInput) {
        final I input = Objects.requireNonNull(pInput, "Input(I) is null");

        final List<Field> fields = new LinkedList<>();
        Class<?> clazz = input.getClass();

        while (shouldRecurseUp(et, clazz)) {
            fields.addAll(Arrays.stream(clazz.getDeclaredFields()).toList());
            clazz = clazz.getSuperclass();
        }

        return fields;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <I> Object fieldToObject(final Field pField, final I input) {
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

    /**
     * Check to see if the given class is in the 'equatable' config bean.
     */
    private <T> boolean shouldRecurseUp(final EquatableThings et, Class<T> clazz) {
        return !et.getDirectlyEquatablePackages().contains(clazz.getPackageName())
                && !et.getDirectlyEquatableClasses().contains(clazz);
    }
}

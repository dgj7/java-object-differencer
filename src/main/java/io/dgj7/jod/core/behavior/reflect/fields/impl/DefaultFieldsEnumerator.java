package io.dgj7.jod.core.behavior.reflect.fields.impl;

import io.dgj7.jod.core.behavior.recurse.predicate.IShouldRecursePredicate;
import io.dgj7.jod.core.behavior.reflect.fields.IFieldsEnumerator;
import io.dgj7.jod.model.config.DifferencerConfiguration;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Default {@link IFieldsEnumerator}.
 * </p>
 */
public class DefaultFieldsEnumerator implements IFieldsEnumerator {
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
}

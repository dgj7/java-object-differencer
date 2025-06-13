package io.dgj7.jod.core.behavior.reflect.fields.impl;

import io.dgj7.jod.core.behavior.recurse.predicate.IShouldRecursePredicate;
import io.dgj7.jod.core.behavior.reflect.fields.IFieldsEnumerator;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.config.EquatableThings;

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
        final EquatableThings et = config.getEquatableThings();

        final List<Field> fields = new LinkedList<>();
        Class<?> clazz = input.getClass();

        while (shouldRecurseUp(et, clazz)) {
            fields.addAll(Arrays.stream(clazz.getDeclaredFields()).toList());
            clazz = clazz.getSuperclass();
        }

        return fields;
    }

    /**
     * Check to see if the given class is in the 'equatable' config bean.
     */
    private <T> boolean shouldRecurseUp(final EquatableThings et, Class<T> clazz) {
        return !et.getDirectlyEquatablePackages().contains(clazz.getPackageName())
                && !et.getDirectlyEquatableClasses().contains(clazz);
    }
}

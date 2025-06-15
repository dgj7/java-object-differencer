package io.dgj7.jod.core.reflect.field.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.core.reflect.field.IFieldFinder;
import io.dgj7.jod.core.reflect.fields.IFieldsEnumerator;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * Default {@link IFieldFinder}.
 * </p>
 */
public class DefaultFieldFinder implements IFieldFinder {
    /**
     * {@inheritDoc}
     */
    @Override
    public <I> Optional<Field> findField(final DifferencerConfiguration config, final I pInput, final String pName) {
        final I input = Objects.requireNonNull(pInput, "Input(I) is null");
        final String name = Objects.requireNonNull(pName, "Name(String) is null");

        final IFieldsEnumerator fe = config.getFieldsEnumerator();

        return fe.fields(config, input)
                .stream()
                .filter(f -> name.equals(f.getName()))
                .findFirst();
    }
}

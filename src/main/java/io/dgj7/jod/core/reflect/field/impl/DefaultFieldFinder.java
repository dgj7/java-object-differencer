package io.dgj7.jod.core.reflect.field.impl;

import io.dgj7.jod.core.reflect.field.IFieldFinder;
import io.dgj7.jod.core.reflect.fields.IFieldsFinder;
import io.dgj7.jod.model.config.DifferencerConfiguration;

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

        final IFieldsFinder fsf = config.getFieldsFinder();

        return fsf.fields(config, input)
                .stream()
                .filter(f -> name.equals(f.getName()))
                .findFirst();
    }
}

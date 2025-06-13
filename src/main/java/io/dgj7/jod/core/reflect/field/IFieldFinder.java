package io.dgj7.jod.core.reflect.field;

import io.dgj7.jod.model.config.DifferencerConfiguration;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * <p>
 * Find a field by name.
 * </p>
 */
public interface IFieldFinder {
    /**
     * Find field with the given name.
     */
    <I> Optional<Field> findField(final DifferencerConfiguration config, final I input, final String name);
}

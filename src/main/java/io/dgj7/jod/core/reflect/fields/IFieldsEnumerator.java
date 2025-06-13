package io.dgj7.jod.core.reflect.fields;

import io.dgj7.jod.config.DifferencerConfiguration;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>
 * Find all fields on an object.
 * </p>
 */
public interface IFieldsEnumerator {
    /**
     * List all {@link Field}s.
     */
    <I> List<Field> fields(final DifferencerConfiguration config, final I input);
}

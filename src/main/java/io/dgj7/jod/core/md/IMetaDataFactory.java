package io.dgj7.jod.core.md;

import io.dgj7.jod.model.config.DifferencerConfiguration;

import java.lang.reflect.Field;

/**
 * <p>
 * Factory of {@link AbstractMetaData}.
 * </p>
 */
// todo: split this into multiple interfaces
public interface IMetaDataFactory<M extends AbstractMetaData> {
    /**
     * Simple factory.
     */
    <T> M from(final Class<T> pClazz);

    /**
     * Simple factory.
     */
    <T> M from(final T pInput);

    /**
     * Simple factory.
     */
    <P> M from(final DifferencerConfiguration config, final Field pField, final P pParent);

    /**
     * Determine the type name.
     */
    <T, U> String describeTypeName(final T expected, final U actual);

    /**
     * Determine the type name.
     */
    <T> String describeTypeName(final T input);
}

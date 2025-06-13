package io.dgj7.jod.metadata;

import io.dgj7.jod.DifferencerConfiguration;

import java.lang.reflect.Field;

/**
 * <p>
 * Factory of {@link AbstractMetaData}.
 * </p>
 */
public interface IMetaDataFactory<M extends AbstractMetaData> {
    /**
     * Simple factory.
     */
    <T> M from(final DifferencerConfiguration config, final Class<T> pClazz);

    /**
     * Simple factory.
     */
    <T> M from(final DifferencerConfiguration config, final T pInput);

    /**
     * Simple factory.
     */
    <T> M from(final DifferencerConfiguration config, final T expected, final T actual);

    /**
     * Simple factory.
     */
    <P> M from(final DifferencerConfiguration config, final Field pField, final P pParent);
}

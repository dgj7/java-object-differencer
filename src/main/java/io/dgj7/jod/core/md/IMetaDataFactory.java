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
    // todo: add differencer configuration parameter
    <T> M from(final Class<T> pClazz);

    /**
     * Simple factory.
     */
    // todo: add differencer configuration parameter
    <T> M from(final T pInput);

    /**
     * Simple factory.
     */
    // todo: add differencer configuration parameter
    <T> M from(final T expected, final T actual);

    /**
     * Simple factory.
     */
    <P> M from(final DifferencerConfiguration config, final Field pField, final P pParent);
}

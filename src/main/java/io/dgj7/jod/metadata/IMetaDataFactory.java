package io.dgj7.jod.metadata;

import io.dgj7.jod.xt.reflect.IFieldFinder;

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
    <T> M fromClass(final Class<T> pClazz);

    /**
     * Simple factory.
     */
    <T> M fromObject(final T pInput);

    /**
     * Simple factory.
     */
    <T> M fromObjects(final T expected, final T actual);

    /**
     * Simple factory.
     */
    <P> M fromField(final IFieldFinder ff, final Field pField, final P pParent);
}

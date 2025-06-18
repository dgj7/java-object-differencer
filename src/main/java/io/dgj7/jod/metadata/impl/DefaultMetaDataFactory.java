package io.dgj7.jod.metadata.impl;

import io.dgj7.jod.metadata.AbstractMetaData;
import io.dgj7.jod.metadata.IMetaDataFactory;
import io.dgj7.jod.xt.reflect.IFieldFinder;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Optional;

/**
 * Default {@link IMetaDataFactory}.
 */
public class DefaultMetaDataFactory implements IMetaDataFactory<DefaultMetaDataFactory.DefaultMetaData> {
    /**
     * <p>
     * Load/store object metadata.
     * </p>
     * <p>
     * This object is intended to be used as the initial
     * way to determine if two root objects or fields represent
     * the same root or field in both objects.
     * </p>
     */
    public static class DefaultMetaData extends AbstractMetaData {
        @Getter
        private final String packageName;
        @Getter
        private final String className;
        @Getter
        private final String fieldName;

        /**
         * Create a new instance.
         */
        private DefaultMetaData(final String pPackageName, final String pClassName, final String pFieldName) {
            this.packageName = Objects.requireNonNull(pPackageName, "PackageName(String) is null");
            this.className = Objects.requireNonNull(pClassName, "ClassName(String) is null");
            this.fieldName = Objects.requireNonNull(pFieldName, "FieldName(String) is null");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String providePackageName() {
            return packageName;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String provideClassName() {
            return className;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String provideFieldName() {
            return fieldName;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String describeTypeName() {
            return getPackageName() + "." + getClassName();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(final Object object) {
            if (object instanceof DefaultMetaData) {
                final DefaultMetaData other = (DefaultMetaData) object;
                return new EqualsBuilder()
                        .append(packageName, other.packageName)
                        .append(className, other.className)
                        .append(fieldName, other.fieldName)
                        .isEquals();
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                    .append(packageName)
                    .append(className)
                    .append(fieldName)
                    .toHashCode();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> DefaultMetaData fromClass(final Class<T> pClazz) {
        final Class<T> clazz = Objects.requireNonNull(pClazz, "Class<T> is null");

        final String packageName = clazz.getPackageName();
        final String className = clazz.getSimpleName();
        final String fieldName = "";

        return new DefaultMetaData(packageName, className, fieldName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> DefaultMetaData fromObject(final T pInput) {
        final T input = Objects.requireNonNull(pInput, "T is null");

        final String packageName = input.getClass().getPackageName();
        final String className = input.getClass().getSimpleName();
        final String fieldName = "";

        return new DefaultMetaData(packageName, className, fieldName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> DefaultMetaData fromObjects(final T expected, final T actual) {
        final T object = Optional.ofNullable(expected).orElse(actual);
        return fromObject(object);
    }

    /**
     * {@inheritDoc}
     */
    public <P> DefaultMetaData fromField(final IFieldFinder ff, final Field pField, final P pParent) {
        final Field field = Objects.requireNonNull(pField, "Field is null");
        final P parent = Objects.requireNonNull(pParent, "Parent(P) is null");

        final Object value = ff.fieldToObject(field, parent);

        final String packageName = value == null ? "" : value.getClass().getPackageName();
        final String className = value == null ? "" : value.getClass().getSimpleName();
        final String fieldName = field.getName();

        return new DefaultMetaData(packageName, className, fieldName);
    }
}

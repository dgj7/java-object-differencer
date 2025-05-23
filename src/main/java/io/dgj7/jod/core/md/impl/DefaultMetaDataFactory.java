package io.dgj7.jod.core.md.impl;

import io.dgj7.jod.core.md.AbstractMetaData;
import io.dgj7.jod.core.md.IMetaDataFactory;
import io.dgj7.jod.core.reflect.IReflection;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.lang.reflect.Field;
import java.util.Objects;

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
    public <T> DefaultMetaData from(final Class<T> pClazz) {
        final Class<T> clazz = Objects.requireNonNull(pClazz, "Class<T> is null");

        final String packageName = clazz.getPackageName();
        final String className = clazz.getSimpleName();
        final String fieldName = "";

        return new DefaultMetaData(packageName, className, fieldName);
    }

    /**
     * {@inheritDoc}
     */
    public <T> DefaultMetaData from(final T pInput) {
        final T input = Objects.requireNonNull(pInput, "T is null");

        final String packageName = input.getClass().getPackageName();
        final String className = input.getClass().getSimpleName();
        final String fieldName = "";

        return new DefaultMetaData(packageName, className, fieldName);
    }

    /**
     * {@inheritDoc}
     */
    public <P> DefaultMetaData from(final DifferencerConfiguration config, final Field pField, final P pParent) {
        final Field field = Objects.requireNonNull(pField, "Field is null");
        final P parent = Objects.requireNonNull(pParent, "Parent(P) is null");
        final IReflection reflect = config.getReflection();

        final Object value = reflect.fieldTo(field, parent);

        final String packageName = value == null ? "" : value.getClass().getPackageName();
        final String className = value == null ? "" : value.getClass().getSimpleName();
        final String fieldName = field.getName();

        return new DefaultMetaData(packageName, className, fieldName);
    }

    /**
     * {@inheritDoc}
     */
    public <T, U> String describeTypeName(final T expected, final U actual) {
        if (expected != null && actual != null) {
            final DefaultMetaData emd = from(expected);
            final DefaultMetaData amd = from(actual);

            if (emd.getPackageName().equals(amd.getPackageName()) && emd.getClassName().equals(amd.getClassName())) {
                return emd.getPackageName() + "." + emd.getClassName();
            } else {
                return emd.getPackageName() + "." + emd.getClassName() + "/" + amd.getPackageName() + "." + amd.getClassName();
            }
        } else if (expected != null) {
            final DefaultMetaData md = from(expected);
            return md.getPackageName() + "." + md.getClassName();
        } else if (actual != null) {
            final DefaultMetaData md = from(actual);
            return md.getPackageName() + "." + md.getClassName();
        } else {
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    public <T> String describeTypeName(final T input) {
        if (input != null) {
            final DefaultMetaData md = from(input);
            return md.getPackageName() + "." + md.getClassName();
        }
        return "";
    }
}

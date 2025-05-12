package io.dgj7.jod.model;

import io.dgj7.jod.core.components.reflect.IReflection;
import io.dgj7.jod.model.config.DiffConfig;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.lang.reflect.Field;
import java.util.Objects;

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
public class Metadata {
    @Getter
    private final String packageName;
    @Getter
    private final String className;
    @Getter
    private final String fieldName;

    /**
     * Create a new instance.
     */
    private Metadata(final String pPackageName, final String pClassName, final String pFieldName) {
        this.packageName = Objects.requireNonNull(pPackageName, "PackageName(String) is null");
        this.className = Objects.requireNonNull(pClassName, "ClassName(String) is null");
        this.fieldName = Objects.requireNonNull(pFieldName, "FieldName(String) is null");
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(final Object object) {
        if (object instanceof Metadata) {
            final Metadata other = (Metadata) object;
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

    /**
     * Factory.
     */
    public static <T> Metadata from(final T pInput) {
        final T input = Objects.requireNonNull(pInput, "T is null");

        final String packageName = input.getClass().getPackageName();
        final String className = input.getClass().getSimpleName();
        final String fieldName = "";

        return new Metadata(packageName, className, fieldName);
    }

    /**
     * Factory.
     */
    public static <P> Metadata from(final DiffConfig config, final Field pField, final P pParent) {
        final Field field = Objects.requireNonNull(pField, "Field is null");
        final P parent = Objects.requireNonNull(pParent, "Parent(P) is null");
        final IReflection reflect = config.getReflection();

        final Object value = reflect.fieldTo(field, parent);

        final String packageName = value == null ? "" : value.getClass().getPackageName();
        final String className = value == null ? "" : value.getClass().getSimpleName();
        final String fieldName = field.getName();

        return new Metadata(packageName, className, fieldName);
    }
}

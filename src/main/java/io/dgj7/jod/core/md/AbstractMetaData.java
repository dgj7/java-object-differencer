package io.dgj7.jod.core.md;

/**
 * Object metadata.
 */
public abstract class AbstractMetaData {
    /**
     * {@inheritDoc}
     */
    public abstract boolean equals(final Object object);

    /**
     * {@inheritDoc}
     */
    public abstract int hashCode();

    /**
     * Provide the package name.
     */
    public abstract String providePackageName();

    /**
     * Provide the class name.
     */
    public abstract String provideClassName();

    /**
     * Provide the field name.
     */
    public abstract String provideFieldName();

    /**
     * Determine the type name.
     */
    public abstract String describeTypeName();
}

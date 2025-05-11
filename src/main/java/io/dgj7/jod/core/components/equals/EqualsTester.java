package io.dgj7.jod.core.components.equals;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.function.BiPredicate;

/**
 * <p>
 * {@link BiPredicate} to determine if two objects are equal.
 * </p>
 */
public class EqualsTester implements BiPredicate<Object, Object> {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(final Object expected, final Object actual) {
        return new EqualsBuilder()
                .append(expected, actual)
                .isEquals();
    }
}

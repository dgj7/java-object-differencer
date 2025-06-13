package io.dgj7.jod.core.behavior.equals;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.function.BiPredicate;

/**
 * <p>
 * {@link BiPredicate} to determine if two objects are equal.
 * </p>
 */
public class DefaultEqualsTester implements BiPredicate<Object, Object> {
    /**
     * {@inheritDoc}
     */
    // todo: add differencer configuration parameter
    @Override
    public boolean test(final Object expected, final Object actual) {
        return new EqualsBuilder()
                .append(expected, actual)
                .isEquals();
    }
}

package io.dgj7.jod.core.behavior.equals;

import io.dgj7.jod.model.config.DifferencerConfiguration;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.function.BiPredicate;

/**
 * <p>
 * {@link BiPredicate} to determine if two objects are equal.
 * </p>
 */
public class DefaultEqualityChecker implements IEqualityChecker {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean check(final DifferencerConfiguration config, final Object expected, final Object actual) {
        return new EqualsBuilder()
                .append(expected, actual)
                .isEquals();
    }
}

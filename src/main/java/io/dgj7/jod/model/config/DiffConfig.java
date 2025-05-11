package io.dgj7.jod.model.config;

import io.dgj7.jod.pattern.builder.Builder;
import io.dgj7.jod.core.reflect.IReflection;
import io.dgj7.jod.core.reflect.impl.DefaultReflectionImpl;
import lombok.Getter;

/**
 * <p>
 * Configuration of differencer.
 * </p>
 */
public class DiffConfig {
    @Getter
    private IReflection reflection;

    /**
     * Create a new instance.
     */
    private DiffConfig() {
        // purposely empty
    }

    /**
     * Builder factory.
     */
    public static DiffConfigBuilder builder() {
        return new DiffConfigBuilder();
    }

    /**
     * Internal builder.
     */
    public static class DiffConfigBuilder implements Builder<DiffConfig> {
        private static final IReflection DEFAULT_REFLECTION = new DefaultReflectionImpl();

        private IReflection theReflection;

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withReflection(final IReflection input) {
            this.theReflection = input;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public DiffConfig build() {
            final DiffConfig configuration = new DiffConfig();

            configuration.reflection = theReflection == null ? DEFAULT_REFLECTION : theReflection;

            return configuration;
        }
    }
}

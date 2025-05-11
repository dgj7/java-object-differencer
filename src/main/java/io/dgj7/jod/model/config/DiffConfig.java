package io.dgj7.jod.model.config;

import io.dgj7.jod.core.components.collection.ICollectionHandler;
import io.dgj7.jod.core.components.collection.IMapHandler;
import io.dgj7.jod.core.components.collection.impl.DefaultCollectionHandler;
import io.dgj7.jod.core.components.collection.impl.DefaultMapHandler;
import io.dgj7.jod.core.components.equals.EqualsTester;
import io.dgj7.jod.core.components.recurse.ShouldRecursePredicate;
import io.dgj7.jod.core.components.reflect.IReflection;
import io.dgj7.jod.core.components.reflect.impl.DefaultReflectionImpl;
import io.dgj7.jod.pattern.builder.Builder;
import lombok.Getter;

import java.util.function.BiPredicate;

/**
 * <p>
 * Configuration of differencer.
 * </p>
 */
public class DiffConfig {
    @Getter
    private IReflection reflection;
    @Getter
    private BiPredicate<Object, Object> shouldRecurse;
    @Getter
    private BiPredicate<Object, Object> equalsTester;
    @Getter
    private ICollectionHandler collectionHandler;
    @Getter
    private IMapHandler mapHandler;

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
        private static final BiPredicate<Object, Object> DEFAULT_RECURSE = new ShouldRecursePredicate();
        private static final BiPredicate<Object, Object> DEFAULT_EQUALS_TESTER = new EqualsTester();
        private static final ICollectionHandler DEFAULT_COLLECTION_HANDLER = new DefaultCollectionHandler();
        private static final IMapHandler DEFAULT_MAP_HANDLER = new DefaultMapHandler();

        private IReflection theReflection;
        private BiPredicate<Object, Object> theShouldRecursePredicate;
        private BiPredicate<Object, Object> theEqualsTester;
        private ICollectionHandler theCollectionHandler;
        private IMapHandler theMapHandler;

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withReflection(final IReflection input) {
            this.theReflection = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withShouldRecursePredicate(final BiPredicate<Object, Object> input) {
            this.theShouldRecursePredicate = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withEqualsTester(final BiPredicate<Object, Object> input) {
            this.theEqualsTester = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withCollectionHandler(final ICollectionHandler input) {
            this.theCollectionHandler = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withMapHandler(final IMapHandler input) {
            this.theMapHandler = input;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public DiffConfig build() {
            final DiffConfig configuration = new DiffConfig();

            configuration.reflection = theReflection == null ? DEFAULT_REFLECTION : theReflection;
            configuration.shouldRecurse = theShouldRecursePredicate == null ? DEFAULT_RECURSE : theShouldRecursePredicate;
            configuration.equalsTester = theEqualsTester == null ? DEFAULT_EQUALS_TESTER : theEqualsTester;
            configuration.collectionHandler = theCollectionHandler == null ? DEFAULT_COLLECTION_HANDLER : theCollectionHandler;
            configuration.mapHandler = theMapHandler == null ? DEFAULT_MAP_HANDLER : theMapHandler;

            return configuration;
        }
    }
}

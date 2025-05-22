package io.dgj7.jod.model.config;

import io.dgj7.jod.core.collections.ICollectionHandler;
import io.dgj7.jod.core.diff.DefaultDifferencerInternals;
import io.dgj7.jod.core.diff.IDifferencerInternals;
import io.dgj7.jod.core.enumerations.IEnumHandler;
import io.dgj7.jod.core.maps.IMapHandler;
import io.dgj7.jod.core.collections.DefaultCollectionHandler;
import io.dgj7.jod.core.enumerations.DefaultEnumHandler;
import io.dgj7.jod.core.maps.DefaultMapHandler;
import io.dgj7.jod.core.equals.EqualsTester;
import io.dgj7.jod.core.nulls.DefaultNullHandler;
import io.dgj7.jod.core.nulls.INullHandler;
import io.dgj7.jod.core.path.root.DefaultRootPathProvider;
import io.dgj7.jod.core.path.root.IRootPathProvider;
import io.dgj7.jod.core.recurse.ShouldRecursePredicate;
import io.dgj7.jod.core.reflect.IReflection;
import io.dgj7.jod.core.reflect.impl.DefaultReflectionImpl;
import io.dgj7.jod.pattern.builder.Builder;
import lombok.Getter;

import java.util.function.BiPredicate;

/**
 * <p>
 * Configuration of differencer.
 * </p>
 */
public class DifferencerConfiguration {
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
    @Getter
    private IEnumHandler enumHandler;
    @Getter
    private IRootPathProvider rootPathProvider;
    @Getter
    private IDifferencerInternals differencerInternals;
    @Getter
    private INullHandler nullHandler;

    /**
     * Create a new instance.
     */
    private DifferencerConfiguration() {
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
    public static class DiffConfigBuilder implements Builder<DifferencerConfiguration> {
        private static final IReflection DEFAULT_REFLECTION = new DefaultReflectionImpl();
        private static final BiPredicate<Object, Object> DEFAULT_RECURSE = new ShouldRecursePredicate();
        private static final BiPredicate<Object, Object> DEFAULT_EQUALS_TESTER = new EqualsTester();
        private static final ICollectionHandler DEFAULT_COLLECTION_HANDLER = new DefaultCollectionHandler();
        private static final IMapHandler DEFAULT_MAP_HANDLER = new DefaultMapHandler();
        private static final IEnumHandler DEFAULT_ENUM_HANDLER = new DefaultEnumHandler();
        private static final IRootPathProvider DEFAULT_ROOT_PATH_PROVIDER = new DefaultRootPathProvider();
        private static final IDifferencerInternals DEFAULT_DIFFERENCER_INTERNALS = new DefaultDifferencerInternals();
        private static final INullHandler DEFAULT_NULL_HANDLER = new DefaultNullHandler();

        private IReflection theReflection;
        private BiPredicate<Object, Object> theShouldRecursePredicate;
        private BiPredicate<Object, Object> theEqualsTester;
        private ICollectionHandler theCollectionHandler;
        private IMapHandler theMapHandler;
        private IEnumHandler theEnumHandler;
        private IRootPathProvider theRootPathProvider;
        private IDifferencerInternals theDifferencerInternals;
        private INullHandler theNullHandler;

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
         * Feed the builder.
         */
        public DiffConfigBuilder withEnumHandler(final IEnumHandler input) {
            this.theEnumHandler = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withRootPathProvider(final IRootPathProvider input) {
            this.theRootPathProvider = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withDifferencerInternals(final IDifferencerInternals input) {
            this.theDifferencerInternals = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withNullHandler(final INullHandler input) {
            this.theNullHandler = input;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public DifferencerConfiguration build() {
            final DifferencerConfiguration configuration = new DifferencerConfiguration();

            configuration.reflection = theReflection == null ? DEFAULT_REFLECTION : theReflection;
            configuration.shouldRecurse = theShouldRecursePredicate == null ? DEFAULT_RECURSE : theShouldRecursePredicate;
            configuration.equalsTester = theEqualsTester == null ? DEFAULT_EQUALS_TESTER : theEqualsTester;
            configuration.collectionHandler = theCollectionHandler == null ? DEFAULT_COLLECTION_HANDLER : theCollectionHandler;
            configuration.mapHandler = theMapHandler == null ? DEFAULT_MAP_HANDLER : theMapHandler;
            configuration.enumHandler = theEnumHandler == null ? DEFAULT_ENUM_HANDLER : theEnumHandler;
            configuration.rootPathProvider = theRootPathProvider == null ? DEFAULT_ROOT_PATH_PROVIDER : theRootPathProvider;
            configuration.differencerInternals = theDifferencerInternals == null ? DEFAULT_DIFFERENCER_INTERNALS : theDifferencerInternals;
            configuration.nullHandler = theNullHandler == null ? DEFAULT_NULL_HANDLER : theNullHandler;

            return configuration;
        }
    }
}

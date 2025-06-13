package io.dgj7.jod.model.config;

import io.dgj7.jod.core.collections.ICollectionHandler;
import io.dgj7.jod.core.collections.id.ICollectionIdentifier;
import io.dgj7.jod.core.collections.id.impl.DefaultCollectionIdentifier;
import io.dgj7.jod.core.collections.impl.DefaultCollectionHandler;
import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.core.diff.IObjectGraphRecursor;
import io.dgj7.jod.core.diff.impl.DefaultObjectDifferencer;
import io.dgj7.jod.core.diff.impl.DefaultObjectGraphRecursor;
import io.dgj7.jod.core.enumerations.IEnumHandler;
import io.dgj7.jod.core.enumerations.impl.DefaultEnumHandler;
import io.dgj7.jod.core.equals.DefaultEqualsTester;
import io.dgj7.jod.core.maps.IMapHandler;
import io.dgj7.jod.core.maps.impl.DefaultMapHandler;
import io.dgj7.jod.core.md.AbstractMetaData;
import io.dgj7.jod.core.md.IMetaDataFactory;
import io.dgj7.jod.core.md.impl.DefaultMetaDataFactory;
import io.dgj7.jod.core.nulls.INullHandler;
import io.dgj7.jod.core.nulls.impl.DefaultNullHandler;
import io.dgj7.jod.core.path.IRootPathProvider;
import io.dgj7.jod.core.path.impl.DefaultRootPathProvider;
import io.dgj7.jod.core.recurse.IShouldRecursePredicate;
import io.dgj7.jod.core.recurse.impl.DefaultShouldRecursePredicate;
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
    private IShouldRecursePredicate shouldRecurse;
    @Getter
    private BiPredicate<Object, Object> equalsTester;
    @Getter
    private ICollectionIdentifier collectionIdentifier;
    @Getter
    private ICollectionHandler collectionHandler;
    @Getter
    private IMapHandler mapHandler;
    @Getter
    private IEnumHandler enumHandler;
    @Getter
    private IRootPathProvider rootPathProvider;
    @Getter
    private IObjectDifferencer objectDifferencer;
    @Getter
    private INullHandler nullHandler;
    @Getter
    private IMetaDataFactory<? extends AbstractMetaData> metaDataFactory;
    @Getter
    private IObjectGraphRecursor recursor;

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
        private static final IShouldRecursePredicate DEFAULT_RECURSE = new DefaultShouldRecursePredicate();
        private static final BiPredicate<Object, Object> DEFAULT_EQUALS_TESTER = new DefaultEqualsTester();
        private static final ICollectionIdentifier DEFAULT_COLLECTION_IDENTIFIER = new DefaultCollectionIdentifier();
        private static final ICollectionHandler DEFAULT_COLLECTION_HANDLER = new DefaultCollectionHandler();
        private static final IMapHandler DEFAULT_MAP_HANDLER = new DefaultMapHandler();
        private static final IEnumHandler DEFAULT_ENUM_HANDLER = new DefaultEnumHandler();
        private static final IRootPathProvider DEFAULT_ROOT_PATH_PROVIDER = new DefaultRootPathProvider();
        private static final IObjectDifferencer DEFAULT_OBJECT_DIFFERENCER = new DefaultObjectDifferencer();
        private static final INullHandler DEFAULT_NULL_HANDLER = new DefaultNullHandler();
        private static final IMetaDataFactory<? extends AbstractMetaData> DEFAULT_META_DATA_FACTORY = new DefaultMetaDataFactory();
        private static final IObjectGraphRecursor DEFAULT_RECURSOR = new DefaultObjectGraphRecursor();

        private IReflection theReflection;
        private IShouldRecursePredicate theShouldRecursePredicate;
        private BiPredicate<Object, Object> theEqualsTester;
        private ICollectionIdentifier theCollectionIdentifier;
        private ICollectionHandler theCollectionHandler;
        private IMapHandler theMapHandler;
        private IEnumHandler theEnumHandler;
        private IRootPathProvider theRootPathProvider;
        private IObjectDifferencer theObjectDifferencer;
        private INullHandler theNullHandler;
        private IMetaDataFactory<? extends AbstractMetaData> theMetaDataFactory;
        private IObjectGraphRecursor theRecursor;

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
        public DiffConfigBuilder withShouldRecursePredicate(final IShouldRecursePredicate input) {
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
        public DiffConfigBuilder withCollectionIdentifier(final ICollectionIdentifier input) {
            this.theCollectionIdentifier = input;
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
        public DiffConfigBuilder withObjectDifferencer(final IObjectDifferencer input) {
            this.theObjectDifferencer = input;
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
         * Feed the builder.
         */
        public DiffConfigBuilder withMetaDataFactory(final IMetaDataFactory<? extends AbstractMetaData> input) {
            this.theMetaDataFactory = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withObjectGraphRecursor(final IObjectGraphRecursor input) {
            this.theRecursor = input;
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
            configuration.collectionIdentifier = theCollectionIdentifier == null ? DEFAULT_COLLECTION_IDENTIFIER : theCollectionIdentifier;
            configuration.collectionHandler = theCollectionHandler == null ? DEFAULT_COLLECTION_HANDLER : theCollectionHandler;
            configuration.mapHandler = theMapHandler == null ? DEFAULT_MAP_HANDLER : theMapHandler;
            configuration.enumHandler = theEnumHandler == null ? DEFAULT_ENUM_HANDLER : theEnumHandler;
            configuration.rootPathProvider = theRootPathProvider == null ? DEFAULT_ROOT_PATH_PROVIDER : theRootPathProvider;
            configuration.objectDifferencer = theObjectDifferencer == null ? DEFAULT_OBJECT_DIFFERENCER : theObjectDifferencer;
            configuration.nullHandler = theNullHandler == null ? DEFAULT_NULL_HANDLER : theNullHandler;
            configuration.metaDataFactory = theMetaDataFactory == null ? DEFAULT_META_DATA_FACTORY : theMetaDataFactory;
            configuration.recursor = theRecursor == null ? DEFAULT_RECURSOR : theRecursor;

            return configuration;
        }
    }
}

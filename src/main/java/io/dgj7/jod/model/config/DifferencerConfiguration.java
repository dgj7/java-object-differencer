package io.dgj7.jod.model.config;

import io.dgj7.jod.core.behavior.collections.detect.ICollectionDetector;
import io.dgj7.jod.core.behavior.collections.detect.impl.DefaultCollectionDetector;
import io.dgj7.jod.core.behavior.collections.diff.ICollectionDifferencer;
import io.dgj7.jod.core.behavior.collections.diff.impl.DefaultCollectionDifferencer;
import io.dgj7.jod.core.behavior.collections.transform.ICollectionTransformer;
import io.dgj7.jod.core.behavior.collections.transform.impl.DefaultCollectionTransformer;
import io.dgj7.jod.core.diff.IObjectDifferencer;
import io.dgj7.jod.core.diff.impl.DefaultObjectDifferencer;
import io.dgj7.jod.core.behavior.enumerations.IEnumDetector;
import io.dgj7.jod.core.behavior.enumerations.impl.DefaultEnumDetector;
import io.dgj7.jod.core.behavior.equals.DefaultEqualsTester;
import io.dgj7.jod.core.behavior.maps.detect.IMapDetector;
import io.dgj7.jod.core.behavior.maps.detect.impl.DefaultMapDetector;
import io.dgj7.jod.core.behavior.maps.diff.IMapDifferencer;
import io.dgj7.jod.core.behavior.maps.diff.impl.DefaultMapDifferencer;
import io.dgj7.jod.core.behavior.maps.transform.IMapTransformer;
import io.dgj7.jod.core.behavior.maps.transform.impl.DefaultMapTransformer;
import io.dgj7.jod.core.behavior.nulls.INullHandler;
import io.dgj7.jod.core.behavior.nulls.impl.DefaultNullHandler;
import io.dgj7.jod.core.behavior.path.IRootPathProvider;
import io.dgj7.jod.core.behavior.path.impl.DefaultRootPathProvider;
import io.dgj7.jod.core.behavior.recurse.action.IObjectGraphRecursor;
import io.dgj7.jod.core.behavior.recurse.action.impl.DefaultObjectGraphRecursor;
import io.dgj7.jod.core.behavior.recurse.predicate.IShouldRecursePredicate;
import io.dgj7.jod.core.behavior.recurse.predicate.impl.DefaultShouldRecursePredicate;
import io.dgj7.jod.core.behavior.reflect.field.IFieldFinder;
import io.dgj7.jod.core.behavior.reflect.field.impl.DefaultFieldFinder;
import io.dgj7.jod.core.behavior.reflect.fields.IFieldsEnumerator;
import io.dgj7.jod.core.behavior.reflect.fields.impl.DefaultFieldsEnumerator;
import io.dgj7.jod.core.behavior.reflect.transform.IFieldTransformer;
import io.dgj7.jod.core.behavior.reflect.transform.impl.DefaultFieldTransformer;
import io.dgj7.jod.core.md.AbstractMetaData;
import io.dgj7.jod.core.md.IMetaDataFactory;
import io.dgj7.jod.core.md.impl.DefaultMetaDataFactory;
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
    private IFieldsEnumerator fieldsEnumerator;
    @Getter
    private IFieldFinder fieldFinder;
    @Getter
    private IFieldTransformer fieldTransformer;
    @Getter
    private IShouldRecursePredicate shouldRecursePredicate;
    @Getter
    private BiPredicate<Object, Object> equalsTester;
    @Getter
    private ICollectionDetector collectionDetector;
    @Getter
    private ICollectionTransformer collectionTransformer;
    @Getter
    private ICollectionDifferencer collectionDifferencer;
    @Getter
    private IMapDetector mapDetector;
    @Getter
    private IMapTransformer mapTransformer;
    @Getter
    private IMapDifferencer mapDifferencer;
    @Getter
    private IEnumDetector enumDetector;
    @Getter
    private IRootPathProvider rootPathProvider;
    @Getter
    private INullHandler nullHandler;
    @Getter
    private IMetaDataFactory<? extends AbstractMetaData> metaDataFactory;
    @Getter
    private IObjectGraphRecursor objectGraphRecursor;

    @Getter
    private IObjectDifferencer objectDifferencer;

    @Getter
    private EquatableThings equatableThings;

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
        private static final IFieldsEnumerator DEFAULT_FIELDS_ENUMERATOR = new DefaultFieldsEnumerator();
        private static final IFieldFinder DEFAULT_FIELD_FINDER = new DefaultFieldFinder();
        private static final IFieldTransformer DEFAULT_FIELD_TRANSFORMER = new DefaultFieldTransformer();
        private static final IShouldRecursePredicate DEFAULT_SHOULD_RECURSE_PREDICATE = new DefaultShouldRecursePredicate();
        private static final BiPredicate<Object, Object> DEFAULT_EQUALS_TESTER = new DefaultEqualsTester();
        private static final ICollectionDetector DEFAULT_COLLECTION_DETECTOR = new DefaultCollectionDetector();
        private static final ICollectionTransformer DEFAULT_COLLECTION_TRANSFORMER = new DefaultCollectionTransformer();
        private static final ICollectionDifferencer DEFAULT_COLLECTION_HANDLER = new DefaultCollectionDifferencer();
        private static final IMapDetector DEFAULT_MAP_DETECTOR = new DefaultMapDetector();
        private static final IMapTransformer DEFAULT_MAP_TRANSFORMER = new DefaultMapTransformer();
        private static final IMapDifferencer DEFAULT_MAP_HANDLER = new DefaultMapDifferencer();
        private static final IEnumDetector DEFAULT_ENUM_DETECTOR = new DefaultEnumDetector();
        private static final IRootPathProvider DEFAULT_ROOT_PATH_PROVIDER = new DefaultRootPathProvider();
        private static final INullHandler DEFAULT_NULL_HANDLER = new DefaultNullHandler();
        private static final IMetaDataFactory<? extends AbstractMetaData> DEFAULT_META_DATA_FACTORY = new DefaultMetaDataFactory();
        private static final IObjectGraphRecursor DEFAULT_OBJECT_GRAPH_RECURSOR = new DefaultObjectGraphRecursor();

        private static final EquatableThings DEFAULT_EQUATABLE_THINGS = EquatableThings.createDefault();

        private static final IObjectDifferencer DEFAULT_OBJECT_DIFFERENCER = new DefaultObjectDifferencer();

        private IFieldsEnumerator theFieldsEnumerator;
        private IFieldFinder theFieldFinder;
        private IFieldTransformer theFieldTransformer;
        private IShouldRecursePredicate theShouldRecursePredicate;
        private BiPredicate<Object, Object> theEqualsTester;
        private ICollectionDetector theCollectionDetector;
        private ICollectionTransformer theCollectionTransformer;
        private ICollectionDifferencer theCollectionHandler;
        private IMapDetector theMapDetector;
        private IMapTransformer theMapTransformer;
        private IMapDifferencer theMapDifferencer;
        private IEnumDetector theEnumDetector;
        private IRootPathProvider theRootPathProvider;
        private INullHandler theNullHandler;
        private IMetaDataFactory<? extends AbstractMetaData> theMetaDataFactory;
        private IObjectGraphRecursor theObjectGraphRecursor;

        private EquatableThings theEquatableThings;

        private IObjectDifferencer theObjectDifferencer;

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withFieldsEnumerator(final IFieldsEnumerator input) {
            this.theFieldsEnumerator = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withFieldFinder(final IFieldFinder input) {
            this.theFieldFinder = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withFieldTransformer(final IFieldTransformer input) {
            this.theFieldTransformer = input;
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
        public DiffConfigBuilder withCollectionDetector(final ICollectionDetector input) {
            this.theCollectionDetector = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withCollectionTransformer(final ICollectionTransformer input) {
            this.theCollectionTransformer = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withCollectionDifferencer(final ICollectionDifferencer input) {
            this.theCollectionHandler = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withMapDetector(final IMapDetector input) {
            this.theMapDetector = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withMapTranformer(final IMapTransformer input) {
            this.theMapTransformer = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withMapDifferencer(final IMapDifferencer input) {
            this.theMapDifferencer = input;
            return this;
        }

        /**
         * Feed the builder.
         */
        public DiffConfigBuilder withEnumDetector(final IEnumDetector input) {
            this.theEnumDetector = input;
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
            this.theObjectGraphRecursor = input;
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
        public DiffConfigBuilder withEquatableThings(final EquatableThings input) {
            this.theEquatableThings = input;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        public DifferencerConfiguration build() {
            final DifferencerConfiguration configuration = new DifferencerConfiguration();

            configuration.fieldsEnumerator = theFieldsEnumerator == null ? DEFAULT_FIELDS_ENUMERATOR : theFieldsEnumerator;
            configuration.fieldFinder = theFieldFinder == null ? DEFAULT_FIELD_FINDER : theFieldFinder;
            configuration.fieldTransformer = theFieldTransformer == null ? DEFAULT_FIELD_TRANSFORMER : theFieldTransformer;
            configuration.shouldRecursePredicate = theShouldRecursePredicate == null ? DEFAULT_SHOULD_RECURSE_PREDICATE : theShouldRecursePredicate;
            configuration.equalsTester = theEqualsTester == null ? DEFAULT_EQUALS_TESTER : theEqualsTester;
            configuration.collectionDetector = theCollectionDetector == null ? DEFAULT_COLLECTION_DETECTOR : theCollectionDetector;
            configuration.collectionTransformer = theCollectionTransformer == null ? DEFAULT_COLLECTION_TRANSFORMER : theCollectionTransformer;
            configuration.collectionDifferencer = theCollectionHandler == null ? DEFAULT_COLLECTION_HANDLER : theCollectionHandler;
            configuration.mapDetector = theMapDetector == null ? DEFAULT_MAP_DETECTOR : theMapDetector;
            configuration.mapTransformer = theMapTransformer == null ? DEFAULT_MAP_TRANSFORMER : theMapTransformer;
            configuration.mapDifferencer = theMapDifferencer == null ? DEFAULT_MAP_HANDLER : theMapDifferencer;
            configuration.enumDetector = theEnumDetector == null ? DEFAULT_ENUM_DETECTOR : theEnumDetector;
            configuration.rootPathProvider = theRootPathProvider == null ? DEFAULT_ROOT_PATH_PROVIDER : theRootPathProvider;
            configuration.nullHandler = theNullHandler == null ? DEFAULT_NULL_HANDLER : theNullHandler;
            configuration.metaDataFactory = theMetaDataFactory == null ? DEFAULT_META_DATA_FACTORY : theMetaDataFactory;
            configuration.objectGraphRecursor = theObjectGraphRecursor == null ? DEFAULT_OBJECT_GRAPH_RECURSOR : theObjectGraphRecursor;

            configuration.objectDifferencer = theObjectDifferencer == null ? DEFAULT_OBJECT_DIFFERENCER : theObjectDifferencer;

            configuration.equatableThings = theEquatableThings == null ? DEFAULT_EQUATABLE_THINGS : theEquatableThings;

            return configuration;
        }
    }
}

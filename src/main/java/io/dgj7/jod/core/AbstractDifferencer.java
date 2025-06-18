package io.dgj7.jod.core;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.metadata.AbstractMetaData;
import io.dgj7.jod.metadata.IMetaDataFactory;
import io.dgj7.jod.model.eq.EquatableThings;
import io.dgj7.jod.xt.collections.detect.ICollectionDetector;
import io.dgj7.jod.xt.collections.transform.ICollectionTransformer;
import io.dgj7.jod.xt.enumerations.IEnumDetector;
import io.dgj7.jod.xt.equals.IEqualityChecker;
import io.dgj7.jod.xt.maps.detect.IMapDetector;
import io.dgj7.jod.xt.maps.transform.IMapTransformer;
import io.dgj7.jod.xt.nulls.INullHandler;
import io.dgj7.jod.xt.path.IRootPathProvider;
import io.dgj7.jod.xt.recurse.IShouldRecursePredicate;
import io.dgj7.jod.xt.reflect.IFieldFinder;

import java.util.Objects;

/**
 * Base for differencers.
 */
public class AbstractDifferencer {
    protected final IFieldFinder ff;
    protected final IShouldRecursePredicate srp;
    protected final IEqualityChecker ec;
    protected final ICollectionDetector cd;
    protected final ICollectionTransformer ct;
    protected final IMapDetector md;
    protected final IMapTransformer mt;
    protected final IEnumDetector ed;
    protected final IRootPathProvider rpp;
    protected final INullHandler nh;
    protected final IMetaDataFactory<? extends AbstractMetaData> mdf;
    protected final EquatableThings et;

    /**
     * Create a new instance.
     */
    protected AbstractDifferencer(final DifferencerConfiguration config) {
        Objects.requireNonNull(config, "DifferencerConfig is null");
        this.ff = config.getFieldFinder();
        this.srp = config.getShouldRecursePredicate();
        this.ec = config.getEqualityChecker();
        this.cd = config.getCollectionDetector();
        this.ct = config.getCollectionTransformer();
        this.md = config.getMapDetector();
        this.mt = config.getMapTransformer();
        this.ed = config.getEnumDetector();
        this.rpp = config.getRootPathProvider();
        this.nh = config.getNullHandler();
        this.mdf = config.getMetaDataFactory();
        this.et = config.getEquatableThings();
    }
}

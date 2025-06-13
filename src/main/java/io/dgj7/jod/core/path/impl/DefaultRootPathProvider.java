package io.dgj7.jod.core.path.impl;

import io.dgj7.jod.core.path.IRootPathProvider;
import io.dgj7.jod.metadata.AbstractMetaData;
import io.dgj7.jod.metadata.IMetaDataFactory;
import io.dgj7.jod.config.DifferencerConfiguration;

/**
 * Default {@link IRootPathProvider}.
 */
public class DefaultRootPathProvider implements IRootPathProvider {
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> String provideRootPath(final DifferencerConfiguration config, final T expected, final T actual) {
        final IMetaDataFactory<? extends AbstractMetaData> mdf = config.getMetaDataFactory();
        final AbstractMetaData md = mdf.from(config, expected, actual);
        return md.describeTypeName();
    }
}

package io.dgj7.jod.core.behavior.path.impl;

import io.dgj7.jod.core.behavior.path.IRootPathProvider;
import io.dgj7.jod.core.md.AbstractMetaData;
import io.dgj7.jod.core.md.IMetaDataFactory;
import io.dgj7.jod.model.config.DifferencerConfiguration;

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
        return mdf.from(expected, actual)
                .map(AbstractMetaData::describeTypeName)
                .orElse("");
    }
}

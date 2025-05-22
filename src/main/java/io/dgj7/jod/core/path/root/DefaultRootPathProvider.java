package io.dgj7.jod.core.path.root;

import io.dgj7.jod.model.Metadata;

/**
 * Default {@link IRootPathProvider}.
 */
public class DefaultRootPathProvider implements IRootPathProvider {
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> String provideRootPath(final T expected, final T actual) {
        if (expected != null) {
            final Metadata md = Metadata.from(expected);
            return md.getPackageName() + "." + md.getClassName();
        } else if (actual != null) {
            final Metadata md = Metadata.from(actual);
            return md.getPackageName() + "." + md.getClassName();
        } else {
            return "";
        }
    }
}

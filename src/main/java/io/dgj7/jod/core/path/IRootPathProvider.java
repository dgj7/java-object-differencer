package io.dgj7.jod.core.path;

import io.dgj7.jod.model.config.DifferencerConfiguration;

/**
 * <p>
 * Determine/provide the root path.
 * </p>
 */
public interface IRootPathProvider {
    <T> String provideRootPath(final DifferencerConfiguration config, final T expected, final T actual);
}

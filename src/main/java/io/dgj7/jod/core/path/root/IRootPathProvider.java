package io.dgj7.jod.core.path.root;

/**
 * <p>
 * Determine/provide the root path.
 * </p>
 */
public interface IRootPathProvider {
    <T> String provideRootPath(final T expected, final T actual);
}

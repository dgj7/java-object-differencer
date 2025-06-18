package io.dgj7.jod.xt.path;

/**
 * <p>
 * Determine/provide the root path.
 * </p>
 */
public interface IRootPathProvider {
    /**
     * Provide the root path.
     */
    <T> String provideRootPath(final T expected, final T actual);
}

package io.dgj7.jod.xt.path.impl;

import io.dgj7.jod.xt.path.IRootPathProvider;

import java.util.Optional;

/**
 * Default {@link IRootPathProvider}.
 */
public class DefaultRootPathProvider implements IRootPathProvider {
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> String provideRootPath(final T expected, final T actual) {
        return Optional.ofNullable(expected)
                .or(() -> Optional.ofNullable(actual))
                .map(ea -> ea.getClass().getName())
                .orElse("");
    }
}

package io.dgj7.jod.core.nulls.impl;

import io.dgj7.jod.core.nulls.INullHandler;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.delta.DeltaType;

import java.util.List;

/**
 * Default {@link INullHandler}.
 */
public class DefaultNullHandler implements INullHandler {
    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void handleNulls(final DifferencerConfiguration config, final String path, final List<Delta> deltas, final T expected, final T actual) {
        if (expected == null && actual == null) {
            return;
        } else if (expected == null || actual == null) {
            deltas.add(Delta.from(config, DeltaType.NULLITY, path, expected, actual));
        } else {
            throw new IllegalStateException("handleNulls() called with no nulls supplied");
        }
    }
}

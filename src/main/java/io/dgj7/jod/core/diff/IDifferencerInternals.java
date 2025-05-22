package io.dgj7.jod.core.diff;

import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;

import java.util.List;

public interface IDifferencerInternals {
    <T> void diffRecurse(final DifferencerConfiguration config, final List<Delta> deltas, final String prefixPath, final T expected, final T actual);

    <T> void diffObjects(final DifferencerConfiguration config, final List<Delta> deltas, final String path, final T expected, final T actual);

    <T> void handleNulls(final String path, final List<Delta> deltas, final T expected, final T actual);
}

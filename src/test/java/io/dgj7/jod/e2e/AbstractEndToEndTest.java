package io.dgj7.jod.e2e;

import io.dgj7.jod.TestBase;
import io.dgj7.jod.core.diff.IDifferencer;
import io.dgj7.jod.core.diff.impl.DefaultDifferencerImpl;
import io.dgj7.jod.model.config.DiffConfig;
import io.dgj7.jod.model.delta.Delta;

import java.util.List;

/**
 * Base for e2e tests.
 */
public class AbstractEndToEndTest extends TestBase {
    private final IDifferencer objectUnderTest = new DefaultDifferencerImpl();
    private final DiffConfig config = DiffConfig.builder()
            .build();

    protected List<Delta> difference(final Object expected, final Object actual) {
        return objectUnderTest.difference(config, expected, actual);
    }
}

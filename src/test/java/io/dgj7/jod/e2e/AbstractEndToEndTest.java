package io.dgj7.jod.e2e;

import io.dgj7.jod.Differencer;
import io.dgj7.jod.TestBase;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;

import java.util.List;

/**
 * Base for e2e tests.
 */
public class AbstractEndToEndTest extends TestBase {
    private final Differencer objectUnderTest = new Differencer();
    private final DifferencerConfiguration config = DifferencerConfiguration.builder()
            .build();

    protected List<Delta> difference(final Object expected, final Object actual) {
        return objectUnderTest.difference(config, expected, actual);
    }
}

package io.dgj7.jod.e2e.btree.notset;

import io.dgj7.jod.Differencer;
import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.model.eq.EquatableThings;
import io.dgj7.jod.testonly.model.binarytree.notset.BinaryNode;

import java.util.List;

/**
 * Base for e2e tests.
 */
public class AbstractBinaryTreeEndToEndTestBase {
    private final Differencer objectUnderTest = new Differencer(DifferencerConfiguration.builder()
            .withEquatableThings(EquatableThings.createDefaultBuilder()
                    .withClass(BinaryNode.class)
                    .build())
            .build());

    protected List<Delta> difference(final Object expected, final Object actual) {
        return objectUnderTest.difference(expected, actual);
    }
}

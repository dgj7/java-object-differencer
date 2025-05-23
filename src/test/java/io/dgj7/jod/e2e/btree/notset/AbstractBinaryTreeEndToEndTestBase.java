package io.dgj7.jod.e2e.btree.notset;

import io.dgj7.jod.Differencer;
import io.dgj7.jod.core.recurse.impl.DefaultShouldRecursePredicate;
import io.dgj7.jod.model.config.DifferencerConfiguration;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.testonly.model.binarytree.notset.BinaryNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Base for e2e tests.
 */
public class AbstractBinaryTreeEndToEndTestBase {
    private final Differencer objectUnderTest = new Differencer();

    protected DifferencerConfiguration.DiffConfigBuilder provideConfigBuilder() {
        return DifferencerConfiguration.builder()
                .withShouldRecursePredicate(new TestOnlyShouldRecursePredicate());
    }

    protected List<Delta> difference(final Object expected, final Object actual) {
        return objectUnderTest.difference(provideConfigBuilder().build(), expected, actual);
    }

    protected class TestOnlyShouldRecursePredicate extends DefaultShouldRecursePredicate {
        @Override
        public List<Class<?>> provideDirectlyEquatableClasses() {
            final List<Class<?>> original = new LinkedList<>(super.provideDirectlyEquatableClasses());
            original.add(BinaryNode.class);
            return original;
        }
    }
}

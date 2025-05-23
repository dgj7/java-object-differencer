package io.dgj7.jod.e2e.btree.notset;

import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.testonly.model.ScenarioVersion;
import io.dgj7.jod.testonly.model.binarytree.notset.BinaryTree;
import io.dgj7.jod.testonly.model.binarytree.notset.BinaryTreeScenarioFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BinaryTreeStringEndToEndTest extends AbstractBinaryTreeEndToEndTestBase {
    @Test
    public final void testNotEqual() {
        final BinaryTree<String> expected = BinaryTreeScenarioFactory.stringScenario(ScenarioVersion.EXPECTED);
        final BinaryTree<String> actual = BinaryTreeScenarioFactory.stringScenario(ScenarioVersion.ACTUAL);

        final List<Delta> result = difference(expected, actual);

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("NOT_EQUAL: io.dgj7.jod.testonly.model.binarytree.notset.BinaryTree.root (io.dgj7.jod.testonly.model.binarytree.notset.BinaryNode): expected=[BNode[15,23,31,38,47,52,7]], actual=[BNode[15,23,31,38,47,52,8]]", result.get(0).toString());
    }

    @Test
    public final void testEqual() {
        final BinaryTree<String> expected = BinaryTreeScenarioFactory.stringScenario(ScenarioVersion.EXPECTED);
        final BinaryTree<String> actual = BinaryTreeScenarioFactory.stringScenario(ScenarioVersion.EXPECTED);

        final List<Delta> result = difference(expected, actual);

        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}

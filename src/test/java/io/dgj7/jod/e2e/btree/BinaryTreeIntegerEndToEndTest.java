package io.dgj7.jod.e2e.btree;

import io.dgj7.jod.e2e.AbstractEndToEndTest;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.testonly.model.ScenarioVersion;
import io.dgj7.jod.testonly.model.btree.BTree;
import io.dgj7.jod.testonly.model.btree.BTreeScenarioFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BinaryTreeIntegerEndToEndTest extends AbstractEndToEndTest {
    @Test
    public final void testNotEqual() {
        final BTree<Integer> expected = BTreeScenarioFactory.integerScenario(ScenarioVersion.EXPECTED);
        final BTree<Integer> actual = BTreeScenarioFactory.integerScenario(ScenarioVersion.ACTUAL);

        final List<Delta> result = difference(expected, actual);

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("NOT_EQUAL: io.dgj7.jod.testonly.model.btree.BTree.root.left.left.value (java.lang.Integer): expected=[7], actual=[8]", result.get(0).toString());
    }

    @Test
    public final void testEqual() {
        final BTree<Integer> expected = BTreeScenarioFactory.integerScenario(ScenarioVersion.EXPECTED);
        final BTree<Integer> actual = BTreeScenarioFactory.integerScenario(ScenarioVersion.EXPECTED);

        final List<Delta> result = difference(expected, actual);

        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.size());
    }
}

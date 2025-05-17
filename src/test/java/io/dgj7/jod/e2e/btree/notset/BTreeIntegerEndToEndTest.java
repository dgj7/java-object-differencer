package io.dgj7.jod.e2e.btree.notset;

import io.dgj7.jod.e2e.AbstractEndToEndTest;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.testonly.model.ScenarioVersion;
import io.dgj7.jod.testonly.model.btree.notset.BTree;
import io.dgj7.jod.testonly.model.btree.notset.BTreeScenarioFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BTreeIntegerEndToEndTest extends AbstractEndToEndTest {
    @Test
    public final void testNotEqual() {
        final BTree<Integer> expected = BTreeScenarioFactory.integerScenario(ScenarioVersion.EXPECTED);
        final BTree<Integer> actual = BTreeScenarioFactory.integerScenario(ScenarioVersion.ACTUAL);

        final List<Delta> result = difference(expected, actual);

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("NOT_EQUAL: io.dgj7.jod.testonly.model.btree.notset.BTree.root.left.left.value (java.lang.Integer): expected=[7], actual=[8]", result.get(0).toString());
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

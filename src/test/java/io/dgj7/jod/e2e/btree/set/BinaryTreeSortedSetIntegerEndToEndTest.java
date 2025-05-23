package io.dgj7.jod.e2e.btree.set;

import io.dgj7.jod.Differencer;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.testonly.model.ScenarioVersion;
import io.dgj7.jod.testonly.model.binarytree.set.BinaryTreeSortedSet;
import io.dgj7.jod.testonly.model.binarytree.set.BinaryTreeSortedSetScenarioFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BinaryTreeSortedSetIntegerEndToEndTest {
    private final Differencer differencer = new Differencer();

    @Test
    public final void testNotEqual() {
        final BinaryTreeSortedSet<Integer> expected = BinaryTreeSortedSetScenarioFactory.integerScenario(ScenarioVersion.EXPECTED);
        final BinaryTreeSortedSet<Integer> actual = BinaryTreeSortedSetScenarioFactory.integerScenario(ScenarioVersion.ACTUAL);

        final List<Delta> results = differencer.difference(expected, actual);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NOT_EQUAL: io.dgj7.jod.testonly.model.binarytree.set.BinaryTreeSortedSet[0] (java.lang.Integer): expected=[7], actual=[8]", results.get(0).toString());
    }

    @Test
    public final void testEqual() {
        final BinaryTreeSortedSet<Integer> expected = BinaryTreeSortedSetScenarioFactory.integerScenario(ScenarioVersion.EXPECTED);
        final BinaryTreeSortedSet<Integer> actual = BinaryTreeSortedSetScenarioFactory.integerScenario(ScenarioVersion.EXPECTED);

        final List<Delta> results = differencer.difference(expected, actual);

        Assert.assertNotNull(results);
        Assert.assertEquals(0, results.size());
    }
}

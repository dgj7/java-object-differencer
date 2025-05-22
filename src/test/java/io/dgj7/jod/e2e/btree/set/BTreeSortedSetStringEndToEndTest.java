package io.dgj7.jod.e2e.btree.set;

import io.dgj7.jod.e2e.AbstractEndToEndTest;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.testonly.model.ScenarioVersion;
import io.dgj7.jod.testonly.model.btree.set.BTreeSortedSet;
import io.dgj7.jod.testonly.model.btree.set.BTreeSortedSetScenarioFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BTreeSortedSetStringEndToEndTest extends AbstractEndToEndTest {
    @Test
    public final void testNotEqual() {
        final BTreeSortedSet<String> expected = BTreeSortedSetScenarioFactory.stringScenario(ScenarioVersion.EXPECTED);
        final BTreeSortedSet<String> actual = BTreeSortedSetScenarioFactory.stringScenario(ScenarioVersion.ACTUAL);

        final List<Delta> results = difference(expected, actual);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NOT_EQUAL: io.dgj7.jod.testonly.model.btree.set.BTreeSortedSet[6] (java.lang.String): expected=[7], actual=[8]", results.get(0).toString());
    }

    @Test
    public final void testEqual() {
        final BTreeSortedSet<String> expected = BTreeSortedSetScenarioFactory.stringScenario(ScenarioVersion.EXPECTED);
        final BTreeSortedSet<String> actual = BTreeSortedSetScenarioFactory.stringScenario(ScenarioVersion.EXPECTED);

        final List<Delta> results = difference(expected, actual);

        Assert.assertNotNull(results);
        Assert.assertEquals(0, results.size());
    }
}

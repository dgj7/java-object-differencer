package io.dgj7.jod.e2e.btree.set;

import io.dgj7.jod.e2e.AbstractEndToEndTest;
import io.dgj7.jod.testonly.model.ScenarioVersion;
import io.dgj7.jod.testonly.model.btree.set.BTreeSortedSet;
import io.dgj7.jod.testonly.model.btree.set.BTreeSortedSetScenarioFactory;
import org.junit.Assert;
import org.junit.Test;

public class BTreeSortedSetStringEndToEndTest extends AbstractEndToEndTest {
    @Test
    public final void testNotEqual() {
        final BTreeSortedSet<String> expected = BTreeSortedSetScenarioFactory.stringScenario(ScenarioVersion.EXPECTED);
        final BTreeSortedSet<String> actual = BTreeSortedSetScenarioFactory.stringScenario(ScenarioVersion.ACTUAL);

        Assert.assertThrows("expected ClassCastException", ClassCastException.class, () -> difference(expected, actual));
    }

    @Test
    public final void testEqual() {
        final BTreeSortedSet<String> expected = BTreeSortedSetScenarioFactory.stringScenario(ScenarioVersion.EXPECTED);
        final BTreeSortedSet<String> actual = BTreeSortedSetScenarioFactory.stringScenario(ScenarioVersion.EXPECTED);

        Assert.assertThrows("expected ClassCastException", ClassCastException.class, () -> difference(expected, actual));
    }
}

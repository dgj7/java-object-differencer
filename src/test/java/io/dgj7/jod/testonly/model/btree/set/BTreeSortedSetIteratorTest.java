package io.dgj7.jod.testonly.model.btree.set;

import io.dgj7.jod.testonly.model.ScenarioVersion;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BTreeSortedSetIteratorTest {
    @Test
    public final void testEmpty() {
        final BTreeSortedSet<Integer> bt = new BTreeSortedSet<>();
        final Iterator<Integer> iter = bt.iterator();

        Assert.assertNotNull(iter);
        Assert.assertFalse(iter.hasNext());

        Assert.assertThrows(NoSuchElementException.class, iter::next);
    }

    @Test
    public final void testValid1() {
        final BTreeSortedSet<Integer> bt = BTreeSortedSetScenarioFactory.integerScenario(ScenarioVersion.EXPECTED);
        final Iterator<Integer> iter = bt.iterator();

        Assert.assertNotNull(iter);

        Assert.assertTrue(iter.hasNext());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(7), iter.next());

        Assert.assertTrue(iter.hasNext());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(15), iter.next());

        Assert.assertTrue(iter.hasNext());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(23), iter.next());

        Assert.assertTrue(iter.hasNext());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(31), iter.next());

        Assert.assertTrue(iter.hasNext());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(38), iter.next());

        Assert.assertTrue(iter.hasNext());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(47), iter.next());

        Assert.assertTrue(iter.hasNext());
        Assert.assertTrue(iter.hasNext());
        Assert.assertEquals(Integer.valueOf(52), iter.next());

        Assert.assertFalse(iter.hasNext());
        Assert.assertFalse(iter.hasNext());
        Assert.assertThrows(NoSuchElementException.class, iter::next);
    }
}

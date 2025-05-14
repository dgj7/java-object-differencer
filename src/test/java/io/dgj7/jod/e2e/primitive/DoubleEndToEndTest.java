package io.dgj7.jod.e2e.primitive;

import io.dgj7.jod.e2e.AbstractEndToEndTest;
import io.dgj7.jod.model.delta.Delta;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DoubleEndToEndTest extends AbstractEndToEndTest {
    @Test
    public final void testExpectedNull() {
        final List<Delta> results = difference(null, 0.5);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.lang.Double (java.lang.Double): expected=[null], actual=[0.5]", results.get(0).toString());
    }

    @Test
    public final void testActualNull() {
        final List<Delta> results = difference(0.75, null);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.lang.Double (java.lang.Double): expected=[0.75], actual=[null]", results.get(0).toString());
    }

    @Test
    public final void testNotEqual() {
        final List<Delta> results = difference(0.75, 0.5);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NOT_EQUAL: java.lang.Double (java.lang.Double): expected=[0.75], actual=[0.5]", results.get(0).toString());
    }

    @Test
    public final void testEqual() {
        final List<Delta> results = difference(0.75, 0.75);

        Assert.assertNotNull(results);
        Assert.assertEquals(0, results.size());
    }
}

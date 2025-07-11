package io.dgj7.jod.e2e.builtin.types;

import io.dgj7.jod.Differencer;
import io.dgj7.jod.model.delta.Delta;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BigDecimalEndToEndTest {
    private final Differencer differencer = new Differencer();

    @Test
    public final void testExpectedNull() {
        final List<Delta> results = differencer.difference(null, BigDecimal.valueOf(0.5));

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.math.BigDecimal (java.math.BigDecimal): expected=[null], actual=[0.5]", results.get(0).toString());
    }

    @Test
    public final void testActualNull() {
        final List<Delta> results = differencer.difference(BigDecimal.valueOf(0.75), null);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.math.BigDecimal (java.math.BigDecimal): expected=[0.75], actual=[null]", results.get(0).toString());
    }

    @Test
    public final void testNotEqual() {
        final List<Delta> results = differencer.difference(BigDecimal.valueOf(0.75), BigDecimal.valueOf(0.5));

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NOT_EQUAL: java.math.BigDecimal (java.math.BigDecimal): expected=[0.75], actual=[0.5]", results.get(0).toString());
    }

    @Test
    public final void testEqual() {
        final List<Delta> results = differencer.difference(BigDecimal.valueOf(0.75), BigDecimal.valueOf(0.75));

        Assert.assertNotNull(results);
        Assert.assertEquals(0, results.size());
    }
}

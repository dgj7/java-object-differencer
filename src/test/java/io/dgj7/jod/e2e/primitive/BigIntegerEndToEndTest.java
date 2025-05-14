package io.dgj7.jod.e2e.primitive;

import io.dgj7.jod.e2e.AbstractEndToEndTest;
import io.dgj7.jod.model.delta.Delta;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InaccessibleObjectException;
import java.math.BigInteger;
import java.util.List;

public class BigIntegerEndToEndTest extends AbstractEndToEndTest {
    @Test
    public final void testExpectedNull() {
        final List<Delta> results = difference(null, BigInteger.valueOf(1000000));

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.math.BigInteger (java.math.BigInteger): expected=[null], actual=[1000000]", results.get(0).toString());
    }

    @Test
    public final void testActualNull() {
        final List<Delta> results = difference(BigInteger.valueOf(9999), null);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: java.math.BigInteger (java.math.BigInteger): expected=[9999], actual=[null]", results.get(0).toString());
    }

    @Test
    public final void testNotEqual() {
        // todo: add-opens on java.math
        expectedException.expect(InaccessibleObjectException.class);

        difference(BigInteger.valueOf(9999), BigInteger.valueOf(1000000));
    }

    @Test
    public final void testEqual() {
        // todo: add-opens on java.math
        expectedException.expect(InaccessibleObjectException.class);

        difference(BigInteger.valueOf(9999), BigInteger.valueOf(9999));
    }
}

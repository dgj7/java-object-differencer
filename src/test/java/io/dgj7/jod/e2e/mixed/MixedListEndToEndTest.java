package io.dgj7.jod.e2e.mixed;

import io.dgj7.jod.Differencer;
import io.dgj7.jod.model.delta.Delta;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("UnnecessaryBoxing")
public class MixedListEndToEndTest {
    private final Differencer differencer = new Differencer();

    @Test
    public final void testNullExpected() {
        final List<Number> expected = null;
        final List<Number> actual = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(1, diffs.size());
        Assert.assertEquals("NULLITY: java.util.ArrayList (java.util.ArrayList): expected=[null], actual=[[1, 2.71828, 3.141592653589793]]", diffs.get(0).toString());
    }

    @Test
    public final void testNullActual() {
        final List<Number> expected = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));
        final List<Number> actual = null;

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(1, diffs.size());
        Assert.assertEquals("NULLITY: java.util.ArrayList (java.util.ArrayList): expected=[[1, 2.71828, 3.141592653589793]], actual=[null]", diffs.get(0).toString());
    }

    @Test
    public final void testMoreLeftExpected() {
        final List<Number> expected = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.7182F), Double.valueOf(3.14159265358979323846));
        final List<Number> actual = Arrays.asList(Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));

        final List<Delta> diffs = differencer.difference(expected, actual);

        // todo: merge collection differencers; the last diff makes no sense, and it's because the default coll-diff goes by index
        Assert.assertNotNull(diffs);
        Assert.assertEquals(4, diffs.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.ArrayList (java.lang.Integer): expected=[3], actual=[2]", diffs.get(0).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.ArrayList[0] (java.lang.Integer): expected=[1], actual=[2.71828]", diffs.get(1).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.ArrayList[1] (java.lang.Float): expected=[2.7182], actual=[3.141592653589793]", diffs.get(2).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.ArrayList[2] (java.lang.Double): no matching element for expected=[3.141592653589793]", diffs.get(3).toString());
    }

    @Test
    public final void testMoreRightExpected() {
        final List<Number> expected = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.7182F), Double.valueOf(3.14159265358979323846));
        final List<Number> actual = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F));

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(3, diffs.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.ArrayList (java.lang.Integer): expected=[3], actual=[2]", diffs.get(0).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.ArrayList[1] (java.lang.Float): expected=[2.7182], actual=[2.71828]", diffs.get(1).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.ArrayList[2] (java.lang.Double): no matching element for expected=[3.141592653589793]", diffs.get(2).toString());
    }

    @Test
    public final void testMoreLeftActual() {
        final List<Number> expected = Arrays.asList(Float.valueOf(2.7182F), Double.valueOf(3.14159265358979323846));
        final List<Number> actual = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(4, diffs.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.ArrayList (java.lang.Integer): expected=[2], actual=[3]", diffs.get(0).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.ArrayList[0] (java.lang.Float): expected=[2.7182], actual=[1]", diffs.get(1).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.ArrayList[1] (java.lang.Double): expected=[3.141592653589793], actual=[2.71828]", diffs.get(2).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.ArrayList[2+1] (java.lang.Double): extra unmatched element; actual=[3.141592653589793]", diffs.get(3).toString());
    }

    @Test
    public final void testMoreRightActual() {
        final List<Number> expected = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.7182F));
        final List<Number> actual = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(3, diffs.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.ArrayList (java.lang.Integer): expected=[2], actual=[3]", diffs.get(0).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.ArrayList[1] (java.lang.Float): expected=[2.7182], actual=[2.71828]", diffs.get(1).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.ArrayList[2+1] (java.lang.Double): extra unmatched element; actual=[3.141592653589793]", diffs.get(2).toString());
    }

    @Test
    public final void testNullLeftExpected() {
        final List<Number> expected = Arrays.asList(null, Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));
        final List<Number> actual = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(2, diffs.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.ArrayList (java.lang.Integer): expected=[4], actual=[3]", diffs.get(0).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.ArrayList[0] (java.lang.String): no matching element for expected=[null]", diffs.get(1).toString());
    }

    @Test
    public final void testNullRightExpected() {
        final List<Number> expected = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846), null);
        final List<Number> actual = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(2, diffs.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.ArrayList (java.lang.Integer): expected=[4], actual=[3]", diffs.get(0).toString());
        Assert.assertEquals("NO_MATCHING_ELEMENT: java.util.ArrayList[3] (java.lang.String): no matching element for expected=[null]", diffs.get(1).toString());
    }

    @Test
    public final void testNullLeftActual() {
        final List<Number> expected = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));
        final List<Number> actual = Arrays.asList(null, Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(5, diffs.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.ArrayList (java.lang.Integer): expected=[3], actual=[4]", diffs.get(0).toString());
        Assert.assertEquals("NULLITY: java.util.ArrayList[0] (java.lang.Integer): expected=[1], actual=[null]", diffs.get(1).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.ArrayList[1] (java.lang.Float): expected=[2.71828], actual=[1]", diffs.get(2).toString());
        Assert.assertEquals("NOT_EQUAL: java.util.ArrayList[2] (java.lang.Double): expected=[3.141592653589793], actual=[2.71828]", diffs.get(3).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.ArrayList[3+1] (java.lang.Double): extra unmatched element; actual=[3.141592653589793]", diffs.get(4).toString());
    }

    @Test
    public final void testNullRightActual() {
        final List<Number> expected = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));
        final List<Number> actual = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846), null);

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(2, diffs.size());
        Assert.assertEquals("COLLECTION_SIZES_NOT_EQUAL: java.util.ArrayList (java.lang.Integer): expected=[3], actual=[4]", diffs.get(0).toString());
        Assert.assertEquals("COLLECTION_EXTRA_ACTUAL_ELEMENT: java.util.ArrayList[3+1]: extra unmatched element; actual=[null]", diffs.get(1).toString());
    }

    @Test
    public final void testExpectedChanged() {
        final List<Number> expected = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.7182F), Double.valueOf(3.14159265358979323846));
        final List<Number> actual = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(1, diffs.size());
        Assert.assertEquals("NOT_EQUAL: java.util.ArrayList[1] (java.lang.Float): expected=[2.7182], actual=[2.71828]", diffs.get(0).toString());
    }

    @Test
    public final void testActualChanged() {
        final List<Number> expected = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));
        final List<Number> actual = Arrays.asList(Integer.valueOf(2), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(1, diffs.size());
        Assert.assertEquals("NOT_EQUAL: java.util.ArrayList[0] (java.lang.Integer): expected=[1], actual=[2]", diffs.get(0).toString());
    }

    @Test
    public final void testEqual() {
        final List<Number> expected = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));
        final List<Number> actual = Arrays.asList(Integer.valueOf(1), Float.valueOf(2.71828F), Double.valueOf(3.14159265358979323846));

        final List<Delta> diffs = differencer.difference(expected, actual);

        Assert.assertNotNull(diffs);
        Assert.assertEquals(0, diffs.size());
    }
}

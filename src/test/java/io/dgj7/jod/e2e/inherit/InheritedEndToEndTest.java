package io.dgj7.jod.e2e.inherit;

import io.dgj7.jod.Differencer;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.testonly.model.ScenarioVersion;
import io.dgj7.jod.testonly.model.inherit.InheritedTypeFactory;
import io.dgj7.jod.testonly.model.inherit.LeafType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class InheritedEndToEndTest {
    private final Differencer differencer = new Differencer();

    @Test
    public final void testNullExpected() {
        final LeafType expected = null;
        final LeafType actual = InheritedTypeFactory.create(ScenarioVersion.EXPECTED);

        final List<Delta> result = differencer.difference(expected, actual);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("NULLITY: io.dgj7.jod.testonly.model.inherit.LeafType (io.dgj7.jod.testonly.model.inherit.LeafType): expected=[null], actual=[LeafType(leafValue=leaf)]", result.get(0).toString());
    }

    @Test
    public final void testNullActual() {
        final LeafType expected = InheritedTypeFactory.create(ScenarioVersion.EXPECTED);
        final LeafType actual = null;

        final List<Delta> result = differencer.difference(expected, actual);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("NULLITY: io.dgj7.jod.testonly.model.inherit.LeafType (io.dgj7.jod.testonly.model.inherit.LeafType): expected=[LeafType(leafValue=leaf)], actual=[null]", result.get(0).toString());
    }

    @Test
    public final void testEqual() {
        final LeafType expected = InheritedTypeFactory.create(ScenarioVersion.EXPECTED);
        final LeafType actual = InheritedTypeFactory.create(ScenarioVersion.EXPECTED);

        final List<Delta> result = differencer.difference(expected, actual);

        Assert.assertEquals(0, result.size());
    }

    @Test
    public final void testNotEqual() {
        final LeafType expected = InheritedTypeFactory.create(ScenarioVersion.EXPECTED);
        final LeafType actual = InheritedTypeFactory.create(ScenarioVersion.ACTUAL);

        final List<Delta> result = differencer.difference(expected, actual);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("NOT_EQUAL: io.dgj7.jod.testonly.model.inherit.LeafType.baseValue (java.lang.String): expected=[base], actual=[modified]", result.get(0).toString());
    }
}

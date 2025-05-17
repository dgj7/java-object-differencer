package io.dgj7.jod.e2e.custom;

import io.dgj7.jod.e2e.AbstractEndToEndTest;
import io.dgj7.jod.model.delta.Delta;
import io.dgj7.jod.testonly.model.ScenarioVersion;
import io.dgj7.jod.testonly.model.business.Business;
import io.dgj7.jod.testonly.model.business.BusinessScenarioFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BusinessEndToEndTest extends AbstractEndToEndTest {
    @Test
    public final void testExpectedNull() {
        final Business expected = null;
        final Business actual = BusinessScenarioFactory.create(ScenarioVersion.EXPECTED);

        final List<Delta> results = difference(expected, actual);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: io.dgj7.jod.testonly.model.business.Business (io.dgj7.jod.testonly.model.business.Business): expected=[null], actual=[Business[Widget Builders]]", results.get(0).toString());
    }

    @Test
    public final void testActualNull() {
        final Business expected = null;
        final Business actual = BusinessScenarioFactory.create(ScenarioVersion.EXPECTED);

        final List<Delta> results = difference(expected, actual);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("NULLITY: io.dgj7.jod.testonly.model.business.Business (io.dgj7.jod.testonly.model.business.Business): expected=[null], actual=[Business[Widget Builders]]", results.get(0).toString());
    }

    @Test
    public final void testNotEqual() {
        // todo: add this test
    }

    @Test
    public final void testEqual() {
        final Business expected = BusinessScenarioFactory.create(ScenarioVersion.EXPECTED);
        final Business actual = BusinessScenarioFactory.create(ScenarioVersion.EXPECTED);

        final List<Delta> results = difference(expected, actual);

        Assert.assertNotNull(results);
        Assert.assertEquals(0, results.size());
    }
}

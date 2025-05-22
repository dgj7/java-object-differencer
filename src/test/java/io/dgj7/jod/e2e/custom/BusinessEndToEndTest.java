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
        final Business expected = BusinessScenarioFactory.create(ScenarioVersion.EXPECTED);
        final Business actual = BusinessScenarioFactory.create(ScenarioVersion.ACTUAL);

        final List<Delta> results = difference(expected, actual);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        System.out.println(results.get(0).toString());
        Assert.assertEquals("NULLITY: io.dgj7.jod.testonly.model.business.Business.employees[PEON] (java.util.LinkedList): expected=[[Employee[PEON: E00000031], Employee[PEON: E00000032], Employee[PEON: E00000033], Employee[PEON: E00000034], Employee[PEON: E00000035], Employee[PEON: E00000036], Employee[PEON: E00000037], Employee[PEON: E00000038], Employee[PEON: E00000039], Employee[PEON: E00000040], Employee[PEON: E00000041], Employee[PEON: E00000042], Employee[PEON: E00000043], Employee[PEON: E00000044], Employee[PEON: E00000045], Employee[PEON: E00000046], Employee[PEON: E00000047], Employee[PEON: E00000048], Employee[PEON: E00000049], Employee[PEON: E00000050], Employee[PEON: E00000051], Employee[PEON: E00000052], Employee[PEON: E00000053], Employee[PEON: E00000054], Employee[PEON: E00000055], Employee[PEON: E00000056], Employee[PEON: E00000057], Employee[PEON: E00000058], Employee[PEON: E00000059], Employee[PEON: E00000060], Employee[PEON: E00000061], Employee[PEON: E00000062], Employee[PEON: E00000063], Employee[PEON: E00000064], Employee[PEON: E00000065], Employee[PEON: E00000066], Employee[PEON: E00000067], Employee[PEON: E00000068], Employee[PEON: E00000069], Employee[PEON: E00000070], Employee[PEON: E00000071], Employee[PEON: E00000072], Employee[PEON: E00000073], Employee[PEON: E00000074], Employee[PEON: E00000075], Employee[PEON: E00000076], Employee[PEON: E00000077], Employee[PEON: E00000078], Employee[PEON: E00000079], Employee[PEON: E00000080], Employee[PEON: E00000081], Employee[PEON: E00000082], Employee[PEON: E00000083], Employee[PEON: E00000084], Employee[PEON: E00000085], Employee[PEON: E00000086], Employee[PEON: E00000087], Employee[PEON: E00000088], Employee[PEON: E00000089], Employee[PEON: E00000090], Employee[PEON: E00000091], Employee[PEON: E00000092], Employee[PEON: E00000093], Employee[PEON: E00000094], Employee[PEON: E00000095], Employee[PEON: E00000096], Employee[PEON: E00000097], Employee[PEON: E00000098], Employee[PEON: E00000099], Employee[PEON: E00000100], Employee[PEON: E00000101], Employee[PEON: E00000102], Employee[PEON: E00000103], Employee[PEON: E00000104], Employee[PEON: E00000105], Employee[PEON: E00000106], Employee[PEON: E00000107], Employee[PEON: E00000108], Employee[PEON: E00000109], Employee[PEON: E00000110], Employee[PEON: E00000111], Employee[PEON: E00000112], Employee[PEON: E00000113], Employee[PEON: E00000114], Employee[PEON: E00000115], Employee[PEON: E00000116], Employee[PEON: E00000117], Employee[PEON: E00000118], Employee[PEON: E00000119], Employee[PEON: E00000120], Employee[PEON: E00000121], Employee[PEON: E00000122], Employee[PEON: E00000123], Employee[PEON: E00000124], Employee[PEON: E00000125], Employee[PEON: E00000126], Employee[PEON: E00000127], Employee[PEON: E00000128], Employee[PEON: E00000129], Employee[PEON: E00000130]]], actual=[null]", results.get(0).toString());
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

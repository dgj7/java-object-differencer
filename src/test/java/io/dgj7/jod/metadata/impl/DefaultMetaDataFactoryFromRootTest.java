package io.dgj7.jod.metadata.impl;

import io.dgj7.jod.metadata.AbstractMetaData;
import io.dgj7.jod.metadata.AbstractMetadataTestBase;
import io.dgj7.jod.metadata.IMetaDataFactory;
import io.dgj7.jod.DifferencerConfiguration;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test {@link DefaultMetaDataFactory#from(DifferencerConfiguration,Object)}.
 */
public class DefaultMetaDataFactoryFromRootTest extends AbstractMetadataTestBase {
    final IMetaDataFactory<DefaultMetaDataFactory.DefaultMetaData> objectUnderTest = new DefaultMetaDataFactory();

    @Test
    public final void testHappyPath() {
        final GenericHost<String, Integer, Boolean> root = new GenericHost<>("hello", 3, false, "generic host", 7);

        final AbstractMetaData output = objectUnderTest.from(config, root);

        Assert.assertNotNull(output);
        Assert.assertEquals("io.dgj7.jod.metadata", output.providePackageName());
        Assert.assertEquals("GenericHost", output.provideClassName());
        Assert.assertEquals("", output.provideFieldName());
    }
}

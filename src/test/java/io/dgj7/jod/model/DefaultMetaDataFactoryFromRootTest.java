package io.dgj7.jod.model;

import io.dgj7.jod.core.md.AbstractMetaData;
import io.dgj7.jod.core.md.DefaultMetaDataFactory;
import io.dgj7.jod.core.md.IMetaDataFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test {@link DefaultMetaDataFactory#from(Object)}.
 */
public class DefaultMetaDataFactoryFromRootTest extends AbstractMetadataTestBase {
    final IMetaDataFactory<DefaultMetaDataFactory.DefaultMetaData> objectUnderTest = new DefaultMetaDataFactory();

    @Test
    public final void testHappyPath() {
        final GenericHost<String, Integer, Boolean> root = new GenericHost<>("hello", 3, false, "generic host", 7);

        final AbstractMetaData output = objectUnderTest.from(root);

        Assert.assertNotNull(output);
        Assert.assertEquals("io.dgj7.jod.model", output.providePackageName());
        Assert.assertEquals("GenericHost", output.provideClassName());
        Assert.assertEquals("", output.provideFieldName());
    }
}

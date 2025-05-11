package io.dgj7.jod.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test {@link Metadata#from(Object)}.
 */
public class MetadataFromRootTest extends AbstractMetadataTestBase {
    @Test
    public final void testHappyPath() {
        final GenericHost<String, Integer, Boolean> root = new GenericHost<>("hello", 3, false, "generic host", 7);

        final Metadata output = Metadata.from(root);

        Assert.assertNotNull(output);
        Assert.assertEquals("io.dgj7.jod.model", output.getPackageName());
        Assert.assertEquals("GenericHost", output.getClassName());
        Assert.assertEquals("", output.getFieldName());
    }
}

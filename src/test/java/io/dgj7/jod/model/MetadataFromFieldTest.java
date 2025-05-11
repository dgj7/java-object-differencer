package io.dgj7.jod.model;

import io.dgj7.jod.model.config.DiffConfig;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Test {@link Metadata#from(DiffConfig, Field, Object)}.
 */
public class MetadataFromFieldTest extends AbstractMetadataTestBase {
    @Test
    public final void testGeneric() {
        final GenericHost<String, Integer, Boolean> root = new GenericHost<>("hello", 3, false, "generic host", 7);
        final Field field = config.getReflection().findField(root, "t").orElseThrow();

        final Metadata output = Metadata.from(config, field, root);

        Assert.assertNotNull(output);
        Assert.assertEquals("java.lang", output.getPackageName());
        Assert.assertEquals("String", output.getClassName());
        Assert.assertEquals("t", output.getFieldName());
    }

    @Test
    public final void testInternal() {
        final GenericHost<String, Integer, Boolean> root = new GenericHost<>("hello", 3, false, "generic host", 7);
        root.setInternal(new GenericHost<>("world", 4, true, "genhost", 8));
        final Field field = config.getReflection().findField(root, "internal").orElseThrow();

        final Metadata output = Metadata.from(config, field, root);

        Assert.assertNotNull(output);
        Assert.assertEquals("io.dgj7.jod.model", output.getPackageName());
        Assert.assertEquals("GenericHost", output.getClassName());
        Assert.assertEquals("internal", output.getFieldName());
    }

    @Test
    public final void testConcrete() {
        final GenericHost<String, Integer, Boolean> root = new GenericHost<>("hello", 3, false, "generic host", 7);
        final Field field = config.getReflection().findField(root, "comment").orElseThrow();

        final Metadata output = Metadata.from(config, field, root);

        Assert.assertNotNull(output);
        Assert.assertEquals("java.lang", output.getPackageName());
        Assert.assertEquals("String", output.getClassName());
        Assert.assertEquals("comment", output.getFieldName());
    }

    @Test
    public final void testPrimitive() {
        final GenericHost<String, Integer, Boolean> root = new GenericHost<>("hello", 3, false, "generic host", 7);
        final Field field = config.getReflection().findField(root, "integer").orElseThrow();

        final Metadata output = Metadata.from(config, field, root);

        Assert.assertNotNull(output);
        Assert.assertEquals("java.lang", output.getPackageName());
        Assert.assertEquals("Integer", output.getClassName());
        Assert.assertEquals("integer", output.getFieldName());
    }
}

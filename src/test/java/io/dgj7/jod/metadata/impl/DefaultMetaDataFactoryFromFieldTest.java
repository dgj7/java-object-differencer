package io.dgj7.jod.metadata.impl;

import io.dgj7.jod.DifferencerConfiguration;
import io.dgj7.jod.metadata.AbstractMetaData;
import io.dgj7.jod.metadata.AbstractMetadataTestBase;
import io.dgj7.jod.metadata.IMetaDataFactory;
import io.dgj7.jod.model.eq.EquatableThings;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Test {@link DefaultMetaDataFactory#from(DifferencerConfiguration, Field, Object)}.
 */
public class DefaultMetaDataFactoryFromFieldTest extends AbstractMetadataTestBase {
    private final IMetaDataFactory<DefaultMetaDataFactory.DefaultMetaData> objectUnderTest = new DefaultMetaDataFactory();
    private final DifferencerConfiguration config = DifferencerConfiguration.builder().build();

    @Test
    public final void testGeneric() {
        final GenericHost<String, Integer, Boolean> root = new GenericHost<>("hello", 3, false, "generic host", 7);
        final Field field = config.getFieldFinder().findField(EquatableThings.createDefault(), root, "t").orElseThrow();

        final AbstractMetaData output = objectUnderTest.from(config, field, root);

        Assert.assertNotNull(output);
        Assert.assertEquals("java.lang", output.providePackageName());
        Assert.assertEquals("String", output.provideClassName());
        Assert.assertEquals("t", output.provideFieldName());
    }

    @Test
    public final void testInternal() {
        final GenericHost<String, Integer, Boolean> root = new GenericHost<>("hello", 3, false, "generic host", 7);
        root.setInternal(new GenericHost<>("world", 4, true, "genhost", 8));
        final Field field = config.getFieldFinder().findField(EquatableThings.createDefault(), root, "internal").orElseThrow();

        final AbstractMetaData output = objectUnderTest.from(config, field, root);

        Assert.assertNotNull(output);
        Assert.assertEquals("io.dgj7.jod.metadata", output.providePackageName());
        Assert.assertEquals("GenericHost", output.provideClassName());
        Assert.assertEquals("internal", output.provideFieldName());
    }

    @Test
    public final void testConcrete() {
        final GenericHost<String, Integer, Boolean> root = new GenericHost<>("hello", 3, false, "generic host", 7);
        final Field field = config.getFieldFinder().findField(EquatableThings.createDefault(), root, "comment").orElseThrow();

        final AbstractMetaData output = objectUnderTest.from(config, field, root);

        Assert.assertNotNull(output);
        Assert.assertEquals("java.lang", output.providePackageName());
        Assert.assertEquals("String", output.provideClassName());
        Assert.assertEquals("comment", output.provideFieldName());
    }

    @Test
    public final void testPrimitive() {
        final GenericHost<String, Integer, Boolean> root = new GenericHost<>("hello", 3, false, "generic host", 7);
        final Field field = config.getFieldFinder().findField(EquatableThings.createDefault(), root, "integer").orElseThrow();

        final AbstractMetaData output = objectUnderTest.from(config, field, root);

        Assert.assertNotNull(output);
        Assert.assertEquals("java.lang", output.providePackageName());
        Assert.assertEquals("Integer", output.provideClassName());
        Assert.assertEquals("integer", output.provideFieldName());
    }
}

package io.dgj7.jod;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * <p>
 * Base for unit tests.
 * </p>
 * <p>
 * Contains utils for testing.
 * </p>
 */
public class TestBase {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
}

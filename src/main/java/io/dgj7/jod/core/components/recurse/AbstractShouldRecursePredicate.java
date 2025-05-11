package io.dgj7.jod.core.components.recurse;

import io.dgj7.jod.model.Metadata;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * <p>
 * Base for "should recurse" predicates.
 * </p>
 */
public abstract class AbstractShouldRecursePredicate implements BiPredicate<Object, Object> {
    protected static final List<String> DIRECTLY_EQUATABLE_PACKAGES = List.of(
            "java.lang",
            "java.util"
    );

    protected boolean isDirectlyEquatable(final Metadata metadata) {
        return DIRECTLY_EQUATABLE_PACKAGES.contains(metadata.getPackageName());
    }
}

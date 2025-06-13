package io.dgj7.jod.model.eq;

import io.dgj7.jod.pattern.builder.Builder;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * Simple pojo storing all the the equatable things.
 * </p>
 * <p>
 * Simply, these are the objects/types/packages/etc into
 * which we don't need to further recurse when comparing.
 * </p>
 */
public class EquatableThings {
    @Getter
    private final List<String> directlyEquatablePackages;
    @Getter
    private final List<Class<?>> directlyEquatableClasses;

    /**
     * Create a new instance.
     */
    private EquatableThings(final List<String> pDirectlyEquatablePackages, final List<Class<?>> pDirectlyEquatableClasses) {
        this.directlyEquatablePackages = List.copyOf(Objects.requireNonNull(pDirectlyEquatablePackages, "DirectlyEquatablePackages(List<String>) is null"));
        this.directlyEquatableClasses = List.copyOf(Objects.requireNonNull(pDirectlyEquatableClasses, "DirectlyEquatableClasses(List<Class<?>>) is null"));
    }

    /**
     * Default builder.
     */
    public static EquatableThingsBuilder createDefaultBuilder() {
        return builder()
                .withPackage("java.lang")
                .withPackage("java.util")
                .withPackage("java.math")
                .withPackage("java.time")
                .withPackage("java.sql")
                ;
    }

    /**
     * Default.
     */
    public static EquatableThings createDefault() {
        return createDefaultBuilder().build();
    }

    /**
     * Builder factory.
     */
    public static EquatableThingsBuilder builder() {
        return new EquatableThingsBuilder();
    }

    /**
     * Builder.
     */
    public static class EquatableThingsBuilder implements Builder<EquatableThings> {
        private final List<String> theDirectlyEquatablePackages = new LinkedList<>();
        private final List<Class<?>> theDirectlyEquatableClasses = new LinkedList<>();

        /**
         * Feed the builder.
         */
        public EquatableThingsBuilder withPackage(final String input) {
            Optional.ofNullable(input).ifPresent(this.theDirectlyEquatablePackages::add);
            return this;
        }

        /**
         * Feed the builder.
         */
        public EquatableThingsBuilder withClass(final Class<?> input) {
            Optional.ofNullable(input).ifPresent(this.theDirectlyEquatableClasses::add);
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public EquatableThings build() {
            return new EquatableThings(theDirectlyEquatablePackages, theDirectlyEquatableClasses);
        }
    }
}

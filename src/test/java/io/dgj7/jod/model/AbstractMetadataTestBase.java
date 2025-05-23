package io.dgj7.jod.model;

import io.dgj7.jod.model.config.DifferencerConfiguration;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Base of tests for {@link DefaultMetaData}.
 * </p>
 */
abstract class AbstractMetadataTestBase {
    protected final DifferencerConfiguration config = DifferencerConfiguration.builder()
            .build();

    static class GenericHost<T, U, V> {
        @Getter
        private final T t;
        @Getter
        private final U u;
        @Getter
        private final V v;
        @Getter
        private final String comment;
        @Getter
        private final int integer;

        @Getter
        @Setter
        private GenericHost internal;

        public GenericHost(final T _t, final U _u, final V _v, final String pComment, final int pInteger) {
            this.t = _t;
            this.u = _u;
            this.v = _v;
            this.comment = pComment;
            this.integer = pInteger;
        }
    }
}

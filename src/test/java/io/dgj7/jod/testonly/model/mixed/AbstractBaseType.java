package io.dgj7.jod.testonly.model.mixed;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
public abstract class AbstractBaseType {
    @Getter
    protected final String baseTypeValue;

    protected AbstractBaseType(final String pBaseTypeValue) {
        this.baseTypeValue = Objects.requireNonNull(pBaseTypeValue, "BaseTypeValue(String) is null");
    }
}

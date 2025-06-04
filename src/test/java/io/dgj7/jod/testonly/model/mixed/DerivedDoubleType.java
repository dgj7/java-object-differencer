package io.dgj7.jod.testonly.model.mixed;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@ToString
public class DerivedDoubleType extends AbstractBaseType {
    @Getter
    private final Double derivedDoubleValue;

    public DerivedDoubleType(final String pBaseTypeValue, final Double pDerivedDoubleValue) {
        super(pBaseTypeValue);
        this.derivedDoubleValue = Objects.requireNonNull(pDerivedDoubleValue, "DerivedDoubleValue(Double) is null");
    }
}

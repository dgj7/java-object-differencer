package io.dgj7.jod.testonly.model.inherit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class LeafType extends AbstractInternalType {
    @Getter
    @Setter
    private String leafValue;
}

package io.dgj7.jod.testonly.model.mixed;

import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@ToString
public class MixedListOwnerType {
    @Getter
    private final List<AbstractBaseType> values = new LinkedList<>();
}

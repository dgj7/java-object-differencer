package io.dgj7.jod.testonly.model.business;

import io.dgj7.jod.testonly.model.business.comp.Address;
import io.dgj7.jod.testonly.model.business.comp.Employee;
import io.dgj7.jod.testonly.model.business.comp.Order;
import io.dgj7.jod.testonly.model.business.def.Title;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Business {
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private Address address;

    @Getter
    private final Map<Title, List<Employee>> employees = new HashMap<>();
    @Getter
    private final List<Order> orders = new LinkedList<>();

    @Override
    public String toString() {
        return "Business[" + title + "]";
    }
}

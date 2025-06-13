package io.dgj7.jod.testonly.model.business;

import io.dgj7.jod.testonly.gen.next.Next;
import io.dgj7.jod.testonly.model.ScenarioVersion;
import io.dgj7.jod.testonly.model.business.comp.Employee;
import io.dgj7.jod.testonly.model.business.comp.Order;
import io.dgj7.jod.testonly.model.business.def.Title;

import java.util.LinkedList;
import java.util.List;

public class BusinessScenarioFactory {
    public static Business create(final ScenarioVersion version) {
        /* start by resetting next factories */
        Next.resetAll();

        /* storage for the returned instance */
        final Business business = new Business();

        /* set all the fields first */
        business.setTitle("Widget Builders");
        business.setAddress(Next.address().next());
        addEmployees(business, version);
        addOrders(business);

        /* now, modify if it's the 'actual' scenario */
        if (ScenarioVersion.ACTUAL.equals(version)) {
            business.getAddress().setPostalCode("10101");
        }

        /* done */
        Next.resetAll();
        return business;
    }

    private static void addEmployees(final Business business, final ScenarioVersion version) {
        Title.titles(version)
                .forEach(title -> {
                    final List<Employee> employees = new LinkedList<>();
                    for (int e = 0; e < title.getEmployeesPerTitle(); e++) {
                        final Employee employee = Next.employee().next();
                        employee.setTitle(title);
                        employees.add(employee);
                    }
                    business.getEmployees().put(title, employees);
                });
    }

    private static void addOrders(final Business business) {
        for (int i = 0; i < 300; i++) {
            final Order order = new Order();

            order.setId(i);
            order.setCustomer(Next.customer().next());
            order.setWidget(Next.widget().next());

            order.setPlaced(Next.processDateTime().next());

            if (i % 3 == 0) {
                order.setPackaged(null);
                order.setShipped(null);
            } else if (i % 3 == 1) {
                order.setPackaged(Next.processDateTime().next());
                order.setShipped(null);
            } else if (i % 3 == 2) {
                order.setPackaged(Next.processDateTime().next());
                order.setShipped(Next.processDateTime().next());
            } else {
                throw new IllegalStateException("how?");
            }

            business.getOrders().add(order);
        }
    }
}

package io.dgj7.jod.testonly.gen.next;

import io.dgj7.jod.testonly.gen.next.impl.*;
import io.dgj7.jod.testonly.model.business.comp.Address;
import io.dgj7.jod.testonly.model.business.comp.Customer;
import io.dgj7.jod.testonly.model.business.comp.Employee;
import io.dgj7.jod.testonly.model.business.comp.Widget;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * Simple testing tool that provides the "next" element
 * of some data type.
 * </p>
 * <p>
 * Ideally, the "next" element will be unique.
 * </p>
 * <p>
 * After reset, it should start back at the initial value again.
 * </p>
 */
public abstract class Next<T> {
    /**
     * Get the next value.
     */
    public abstract T next();

    /**
     * Reset the value.
     */
    public abstract void reset();

    /**
     * Reset method for all factories.
     */
    public static void resetAll() {
        NextAddress.instance().reset();
        NextBirthDate.instance().reset();
        NextEmployee.instance().reset();
        NextWidget.instance().reset();
        NextProcessDateTime.instance().reset();
        NextCustomer.instance().reset();
    }

    public static Next<Address> address() {
        return NextAddress.instance();
    }

    public static Next<LocalDate> birthDate() {
        return NextBirthDate.instance();
    }

    public static Next<Employee> employee() {
        return NextEmployee.instance();
    }

    public static Next<Widget> widget() {
        return NextWidget.instance();
    }

    public static Next<LocalDateTime> processDateTime() {
        return NextProcessDateTime.instance();
    }

    public static Next<Customer> customer() {
        return NextCustomer.instance();
    }
}

package io.dgj7.jod.testonly.gen.next.impl;

import io.dgj7.jod.testonly.model.business.comp.Employee;
import org.apache.commons.lang3.StringUtils;

public class NextEmployee extends AbstractNextPerson<Employee> {
    private static NextEmployee INSTANCE;
    private static final int EMPLOYEE_NUM_START = 1;

    private int employeeNum = EMPLOYEE_NUM_START;

    private NextEmployee() {
        // purposely empty
    }

    public static NextEmployee instance() {
        if (INSTANCE == null) {
            synchronized (NextEmployee.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NextEmployee();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Employee next() {
        final Employee employee = new Employee();

        employee.setEmployeeId("E" + StringUtils.leftPad("" + employeeNum, 8, '0'));
        populate(employee);

        return employee;
    }

    @Override
    public void reset() {
        employeeNum = EMPLOYEE_NUM_START;
    }
}

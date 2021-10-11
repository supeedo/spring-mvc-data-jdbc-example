package ru.study.validation;

import ru.study.model.Employee;

public class EmployeeValidator implements Validator<Employee> {
    private static final String ERROR = " must not be empty, contains only letters, and be from 2 to 23 characters long";
    private final String namePattern = "^([А-ЯA-Z]{1}[а-яёa-z]{1,23})$|^([А-ЯA-Z]{1}[а-яёa-z]{1,23})+\\s([А-ЯA-Zа-яёa-z]{1}[а-яёa-z]{1,23})$";
    private final ValidationResult vr;

    public EmployeeValidator() {
        this.vr = new ValidationResult();
    }

    private EmployeeValidator checkFirstName(Employee employee) {
        if (!employee.getFirstName().matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("First Name" + ERROR);
        }
        return this;
    }

    private EmployeeValidator checkLastName(Employee employee) {
        if (!employee.getLastName().matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("Last Name" + ERROR);
        }
        return this;
    }

    private EmployeeValidator checkRole(Employee employee) {
        if (!employee.getRole().matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("Role" + ERROR);
        }
        return this;
    }

    @Override
    public ValidationResult validate(Employee employee) {
        checkFirstName(employee)
                .checkLastName(employee)
                .checkRole(employee);
        return vr;
    }
}

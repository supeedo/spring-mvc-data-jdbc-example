package ru.study.validation;

import ru.study.model.Employee;

public class EmployeeValidator implements Validator<Employee> {
    private static final String ERROR_MESSAGE = " must not be empty, contains only letters, and be from 2 to 23 characters long";
    private final String namePattern = "^([А-ЯA-Z]{1}[а-яёa-z]{1,23})$|^([А-ЯA-Z]{1}[а-яёa-z]{1,23})+\\s([А-ЯA-Zа-яёa-z]{1}[а-яёa-z]{1,23})$";
    private final ValidationResult vr;

    public EmployeeValidator() {
        this.vr = new ValidationResult();
    }

    private void checkFirstName(String firstName) {
        if (!firstName.matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("First Name" + ERROR_MESSAGE);
        }
    }

    private void checkLastName(String lastName) {
        if (!lastName.matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("Last Name" + ERROR_MESSAGE);
        }
    }

    private void checkRole(String role) {
        if (!role.matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("Role" + ERROR_MESSAGE);
        }
    }

    @Override
    public ValidationResult validate(Employee employee) {
        checkFirstName(employee.getFirstName());
        checkLastName(employee.getLastName());
        checkRole(employee.getRole());
        return vr;
    }
}

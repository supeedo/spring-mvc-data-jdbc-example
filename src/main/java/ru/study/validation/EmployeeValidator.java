package ru.study.validation;

import ru.study.model.Employee;

public class EmployeeValidator implements Validator<Employee>{
    private static final String ERROR = " must not be empty, contains only letters, and be from 2 to 23 characters long";

    @Override
    public ValidationResult validate(Employee employee) {
        String namePattern = "^([А-ЯA-Z]{1}[а-яёa-z]{1,23})$|^([А-ЯA-Z]{1}[а-яёa-z]{1,23})+\\s([А-ЯA-Zа-яёa-z]{1}[а-яёa-z]{1,23})$";
        final ValidationResult vr = new ValidationResult();
        if (!employee.getFirstName().matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("First Name" + ERROR);
        }
        if (!employee.getLastName().matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("Last Name" + ERROR);
        }
        if (!employee.getRole().matches(namePattern)) {
            vr.setValid(false);
            vr.inputMessage("Role" + ERROR);
        }
        return vr;
    }
}

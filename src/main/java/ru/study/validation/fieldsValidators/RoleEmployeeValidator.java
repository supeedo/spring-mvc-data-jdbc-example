package ru.study.validation.fieldsValidators;

import ru.study.model.Employee;
import ru.study.validation.ValidationResult;
import ru.study.validation.Validator;

public class RoleEmployeeValidator implements Validator<Employee> {
    private static final String ERROR_MESSAGE = "Role must not be empty, contains only letters, and be from 2 to 23 characters long";
    private static final String PATTERN = "^([А-ЯA-Z]{1}[а-яёa-z]{1,23})$";

    @Override
    public ValidationResult validate(Employee employee) {
        ValidationResult vr = new ValidationResult();
        if (!employee.getFirstName().matches(PATTERN)) {
            vr.setValid(false);
            vr.inputMessage(ERROR_MESSAGE);
        }
        return vr;
    }
}

package ru.study.validation.fieldsValidators;

import ru.study.validation.ValidationResult;
import ru.study.validation.Validator;

public class RoleEmployeeValidator implements Validator<String> {
    private static final String ERROR_MESSAGE = "Role must not be empty, contains only letters, and be from 2 to 23 characters long";
    private static final String PATTERN = "^([А-ЯA-Z]{1}[а-яёa-z]{1,23})$|^([А-ЯA-Z]{1}[а-яёa-z]{1,23})+\\s([А-ЯA-Zа-яёa-z]{1}[а-яёa-z]{1,23})$";

    @Override
    public ValidationResult validate(String role) {
        ValidationResult vr = new ValidationResult();
        if (!role.matches(PATTERN)) {
            vr.setValid(false);
            vr.inputMessage(ERROR_MESSAGE);
        }
        return vr;
    }
}

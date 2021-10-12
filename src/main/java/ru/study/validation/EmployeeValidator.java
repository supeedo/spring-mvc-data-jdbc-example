package ru.study.validation;

import ru.study.model.Employee;
import ru.study.validation.fieldsValidators.FirstNameEmployeeValidator;
import ru.study.validation.fieldsValidators.LastNameEmployeeValidator;
import ru.study.validation.fieldsValidators.RoleEmployeeValidator;

import java.util.HashSet;
import java.util.Set;

public class EmployeeValidator implements Validator<Employee> {
    private final ValidationResult validResult;
    private final Set<Validator<Employee>> validators;

    public EmployeeValidator() {
        this.validResult = new ValidationResult();
        this.validators = new HashSet<>();
        validators.add(new FirstNameEmployeeValidator());
        validators.add(new LastNameEmployeeValidator());
        validators.add(new RoleEmployeeValidator());
    }

    @Override
    public ValidationResult validate(Employee employee) {
        for (Validator<Employee> vl : validators) {
            validResult.merge(vl.validate(employee));
        }
        return validResult;
    }
}

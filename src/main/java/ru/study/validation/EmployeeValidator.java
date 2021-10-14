package ru.study.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.exceptions.ModelException;
import ru.study.model.Employee;
import ru.study.validation.fieldsValidators.FirstNameEmployeeValidator;
import ru.study.validation.fieldsValidators.LastNameEmployeeValidator;
import ru.study.validation.fieldsValidators.RoleEmployeeValidator;

import java.util.HashMap;
import java.util.Map;

import static ru.study.exceptions.ModelException.ErrorCode.GETTING_DATA_FROM_MODEL_ERROR;

public class EmployeeValidator implements Validator<Employee> {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeValidator.class);

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ROLE = "role";

    private final ValidationResult validResult;
    private final Map<String, Validator<String>> validators;

    public EmployeeValidator() {
        this.validResult = new ValidationResult();
        this.validators = new HashMap<>();
        validators.put(FIRST_NAME, new FirstNameEmployeeValidator());
        validators.put(LAST_NAME, new LastNameEmployeeValidator());
        validators.put(ROLE, new RoleEmployeeValidator());
    }

    @Override
    public ValidationResult validate(Employee employee) throws RuntimeException {
        for (Map.Entry<String, Validator<String>> validatorEntry : validators.entrySet()) {
            final String valueField = getValueFromFieldModelByFieldName(validatorEntry.getKey(), employee);
            validResult
                    .merge(validatorEntry
                            .getValue()
                            .validate(valueField));
        }
        return validResult;
    }

    private String getValueFromFieldModelByFieldName(String fieldName, Employee employee) throws RuntimeException {
        switch (fieldName) {
            case FIRST_NAME:
                return employee.getFirstName();
            case LAST_NAME:
                return employee.getLastName();
            case ROLE:
                return employee.getRole();
            default:
                LOG.error("Requesting an invalid field: {}, from the model: {}", fieldName, employee);
                throw new ModelException("There is no expected field in the model", GETTING_DATA_FROM_MODEL_ERROR);
        }
    }
}

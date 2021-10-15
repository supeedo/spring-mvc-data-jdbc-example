package ru.study.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.exceptions.ModelException;
import ru.study.model.Employee;
import ru.study.validation.fieldsExtectors.Extractor;
import ru.study.validation.fieldsExtectors.FirstNameEmployeeExtractorImpl;
import ru.study.validation.fieldsExtectors.LastNameEmployeeExtractorImpl;
import ru.study.validation.fieldsExtectors.RoleEmployeeExtractorImpl;
import ru.study.validation.fieldsValidators.FirstNameEmployeeValidator;
import ru.study.validation.fieldsValidators.LastNameEmployeeValidator;
import ru.study.validation.fieldsValidators.RoleEmployeeValidator;

import java.util.HashMap;
import java.util.Map;

import static ru.study.exceptions.ModelException.ErrorCode.GETTING_DATA_FROM_MODEL_ERROR;

public class EmployeeValidator implements Validator<Employee> {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeValidator.class);

    private final ValidationResult validResult;
    private final Map<Extractor<Employee>, Validator<String>> validators;

    public EmployeeValidator() {
        this.validResult = new ValidationResult();
        this.validators = new HashMap<>();
        validators.put(new FirstNameEmployeeExtractorImpl(), new FirstNameEmployeeValidator());
        validators.put(new LastNameEmployeeExtractorImpl(), new LastNameEmployeeValidator());
        validators.put(new RoleEmployeeExtractorImpl(), new RoleEmployeeValidator());
    }

    @Override
    public ValidationResult validate(Employee employee) throws RuntimeException {
        for (Map.Entry<Extractor<Employee>, Validator<String>> validatorEntry : validators.entrySet()) {
            String extractValue = String.valueOf(validatorEntry.getKey()
                    .getValueFromFieldModel(employee)
                    .orElseThrow(() -> new ModelException("Error extract data from model", GETTING_DATA_FROM_MODEL_ERROR)));
            validResult.merge(validatorEntry.getValue().validate(extractValue));
        }
        return validResult;
    }
}

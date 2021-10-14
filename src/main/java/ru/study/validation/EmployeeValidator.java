package ru.study.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.model.Employee;
import ru.study.validation.fieldsExtectors.EmployeeExtractorImpl;
import ru.study.validation.fieldsExtectors.Extractor;
import ru.study.validation.fieldsValidators.FirstNameEmployeeValidator;
import ru.study.validation.fieldsValidators.LastNameEmployeeValidator;
import ru.study.validation.fieldsValidators.RoleEmployeeValidator;

import java.util.HashMap;
import java.util.Map;

public class EmployeeValidator implements Validator<Employee> {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeValidator.class);

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ROLE = "role";

    private final ValidationResult validResult;
    private final Map<String, Validator<String>> validators;
    private final Extractor<Employee> extractor;

    public EmployeeValidator() {
        this.validResult = new ValidationResult();
        this.extractor =  new EmployeeExtractorImpl();
        this.validators = new HashMap<>();
        validators.put(FIRST_NAME, new FirstNameEmployeeValidator());
        validators.put(LAST_NAME, new LastNameEmployeeValidator());
        validators.put(ROLE, new RoleEmployeeValidator());
    }

    @Override
    public ValidationResult validate(Employee employee) throws RuntimeException {
        for (Map.Entry<String, Validator<String>> validatorEntry : validators.entrySet()) {
            final String valueField = extractor.getValueFromFieldModelByFieldName(validatorEntry.getKey(), employee);
            validResult
                    .merge(validatorEntry
                            .getValue()
                            .validate(valueField));
        }
        return validResult;
    }
}

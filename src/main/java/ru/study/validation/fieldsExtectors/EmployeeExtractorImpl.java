package ru.study.validation.fieldsExtectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.exceptions.ModelException;
import ru.study.model.Employee;

import static ru.study.exceptions.ModelException.ErrorCode.GETTING_DATA_FROM_MODEL_ERROR;

public class EmployeeExtractorImpl implements Extractor<Employee> {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeExtractorImpl.class);

    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String ROLE = "role";

    @Override
    public String getValueFromFieldModelByFieldName(String fieldName, Employee employee) throws RuntimeException {
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

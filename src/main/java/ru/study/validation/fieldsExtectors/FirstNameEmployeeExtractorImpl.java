package ru.study.validation.fieldsExtectors;

import ru.study.model.Employee;

public class FirstNameEmployeeExtractorImpl implements Extractor<Employee> {

    @Override
    public String getValueFromFieldModel(Employee employee) throws RuntimeException {
        return employee.getFirstName();
    }

}

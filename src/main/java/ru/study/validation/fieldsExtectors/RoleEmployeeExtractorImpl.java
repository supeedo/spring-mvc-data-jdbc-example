package ru.study.validation.fieldsExtectors;

import ru.study.model.Employee;

import java.util.Optional;

public class RoleEmployeeExtractorImpl implements Extractor<Employee> {
    @Override
    public Optional<String> getValueFromFieldModel(Employee employee) throws RuntimeException {
        return Optional.of(employee.getRole());
    }
}

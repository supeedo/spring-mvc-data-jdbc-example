package ru.study.validation.fieldsExtectors;

import ru.study.model.Employee;

import java.util.Optional;

public class RoleEmployeeExtractorImpl implements Extractor<Employee, String> {
    @Override
    public Optional<String> getValueFromFieldModel(Employee employee) throws RuntimeException {
        return employee == null ? Optional.empty() : Optional.of(employee.getRole());
    }
}

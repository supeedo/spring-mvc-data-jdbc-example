package ru.study.service;

import ru.study.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    void deleteEmployeeById(Long id);

    void createNewEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void saveEmployee(Employee employee);
}

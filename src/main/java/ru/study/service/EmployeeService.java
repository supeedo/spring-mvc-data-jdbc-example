package ru.study.service;

import ru.study.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmp();

    Employee getEmpById(Long id);

    void addEmp(Employee employee);
}

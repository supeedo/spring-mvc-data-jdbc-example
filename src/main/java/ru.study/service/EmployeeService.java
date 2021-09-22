package ru.study.service;

import ru.study.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmp();
    List<Employee> getEmpByRole(String role);
    Employee getEmpById(long id);
}

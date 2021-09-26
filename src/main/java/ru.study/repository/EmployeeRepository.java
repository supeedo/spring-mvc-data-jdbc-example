package ru.study.repository;

import ru.study.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getData();
    void setData(List<Employee> list);
}

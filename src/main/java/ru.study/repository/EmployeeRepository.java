package ru.study.repository;

import ru.study.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getListOfModel();
    void setListOfModel(List<Employee> list);
}

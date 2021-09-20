package ru.study.repository;

import ru.study.model.EmployeeDTO;

import java.util.List;

public interface EmployeeRepository {
    List<EmployeeDTO> getData();
}

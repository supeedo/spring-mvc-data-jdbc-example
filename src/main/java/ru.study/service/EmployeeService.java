package ru.study.service;

import ru.study.model.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmp();
    List<EmployeeDTO> getEmpByRole(String role);
    EmployeeDTO getEmpById(long id);
}

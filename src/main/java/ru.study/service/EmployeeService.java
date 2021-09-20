package ru.study.service;

import ru.study.model.EmployeeDTO;
import ru.study.repository.EmployeeRepositoryCSVImpl;
import java.util.List;

public class EmployeeService {

    private final EmployeeRepositoryCSVImpl empRepo;

    public EmployeeService() {
        this.empRepo = new EmployeeRepositoryCSVImpl();
    }

    public List<EmployeeDTO> getAllEmp(){
        return  empRepo.getData();
    }
}

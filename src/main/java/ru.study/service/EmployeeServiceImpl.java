package ru.study.service;

import ru.study.exceptions.ResourceException;
import ru.study.model.EmployeeDTO;
import ru.study.repository.EmployeeRepositoryCSVImpl;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepositoryCSVImpl empRepo;

    public EmployeeServiceImpl() {
        this.empRepo = new EmployeeRepositoryCSVImpl();
    }

    public List<EmployeeDTO> getAllEmp(){
        return  empRepo.getData();
    }

    @Override
    public List<EmployeeDTO> getEmpByRole(String role) {
        return empRepo.getData()
                .stream()
                .filter(x->x.getRole().equals(role))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmpById(long id) throws ResourceException {
        return empRepo.getData()
                .stream()
                .filter(x->x.getId() == id)
                .findAny()
                .orElse(new EmployeeDTO(0, "", "", ""));
    }
}

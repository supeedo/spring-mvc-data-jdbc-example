package ru.study.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.exceptions.ResourceException;
import ru.study.model.EmployeeDTO;
import ru.study.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository empRepo;

    public EmployeeServiceImpl(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    public List<EmployeeDTO> getAllEmp() {
        return empRepo.getData();
    }

    @Override
    public List<EmployeeDTO> getEmpByRole(final String role) {
        logger.debug("Запрос по роли: {}", role);
        return empRepo.getData()
                .stream()
                .filter(x -> x.getRole().equals(role))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmpById(final long id) throws ResourceException {
        logger.debug("Запрос по id: {}", id);
        return empRepo.getData()
                .stream()
                .filter(x -> x.getId() == id)
                .findAny()
                .orElse(new EmployeeDTO(0, "", "", ""));
    }
}

package ru.study.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.study.exceptions.ResourceException;
import ru.study.model.Employee;
import ru.study.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository empRepo;

    public EmployeeServiceImpl(EmployeeRepository empRepo) {
        this.empRepo = empRepo;
    }

    public List<Employee> getAllEmp() {
        return empRepo.getListOfModel();
    }

    @Override
    public List<Employee> getEmpByRole(final String role) {
        logger.debug("Запрос по роли: {}", role);
        return empRepo.getListOfModel()
                .stream()
                .filter(x -> x.getRole().equals(role))
                .collect(Collectors.toList());
    }

    @Override
    public Employee getEmpById(final long id) throws ResourceException {
        logger.debug("Запрос по id: {}", id);
        return empRepo.getListOfModel()
                .stream()
                .filter(x -> x.getId() == id)
                .findAny()
                .orElse(new Employee(0, "", "", ""));
    }

    @Override
    public void addEmp(Employee employee) {
        logger.debug("Добавлен объект: {}", employee);
        AtomicLong id = new AtomicLong();
        List<Employee> employees = empRepo.getListOfModel();
        employees.stream()
                .max(Comparator.comparing(Employee::getId))
                .ifPresent(x -> id.set(x.getId() + 1));
        employee.setId(id.get());
        employees.add(employee);
        empRepo.setListOfModel(employees);
    }
}

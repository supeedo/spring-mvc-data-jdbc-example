package ru.study.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.model.Employee;
import ru.study.repository.EmployeeRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>((Collection<? extends Employee>) employeeRepo.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Employee getEmployeeById(final Long id) {
        return employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Transactional
    @Override
    public void deleteEmployeeById(final Long id) {
        employeeRepo.deleteById(id);
    }

    @Transactional
    @Override
    public void createNewEmployee(final Employee employee) {
        employeeRepo.save(employee);
    }

    @Transactional
    @Override
    public void updateEmployee(final Employee employee) {
        Employee emp = employeeRepo.findById(employee.getId()).orElseThrow(() -> new RuntimeException("Book not found"));
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmployeeRoleId(employee.getEmployeeRoleId());
    }

    @Transactional
    @Override
    public void saveEmployee(final Employee employee) {
        employeeRepo.save(employee);
    }
}

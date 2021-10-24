package ru.study.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.study.exceptions.ResourceException;
import ru.study.model.Employee;
import ru.study.repository.EmployeeRepo;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getAllEmp() {
        return null;
    }


    @Override
    public Employee getEmpById(final Long id) throws ResourceException {
//        logger.debug("Запрос по id: {}", id);
//        return empRepo.getListOfModel()
//                .stream()
//                .filter(x -> x.getId() == id)
//                .findAny()
//                .orElse(new Employee(0, "", "", ""));
        return null;
    }

    @Override
    public void addEmp(Employee employee) {
//        logger.debug("Добавлен объект: {}", employee);
//        AtomicLong id = new AtomicLong();
//        List<Employee> employees = empRepo.getListOfModel();
//        employees.stream()
//                .max(Comparator.comparing(Employee::getId))
//                .ifPresent(x -> id.set(x.getId() + 1));
//        employee.setId(id.get());
//        employees.add(employee);
//        empRepo.setListOfModel(employees);
    }
}

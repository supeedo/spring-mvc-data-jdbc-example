package ru.study.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.study.model.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}

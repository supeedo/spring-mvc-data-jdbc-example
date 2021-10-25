package ru.study.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.study.model.Employee;
import ru.study.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(@Qualifier(value = "employeeServiceImpl") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    List<Employee> getAllEmployee() {
        return null;
    }

    @GetMapping("/{id}")
    Employee getEmployee(@PathVariable final Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    Employee updateEmployee(@RequestBody final Employee employee) {
        return null;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    void createEmployee(@RequestBody final Employee employee) {
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteEmployee(@PathVariable final Long id) {
    }


}

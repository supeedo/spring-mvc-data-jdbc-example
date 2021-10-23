package ru.study.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping("/")
    String getAllEmployee() {
        return null;
    }

    @GetMapping("/{id}")
    String getEmployee(@PathVariable String id) {
        return null;
    }
}

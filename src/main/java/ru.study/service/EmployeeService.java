package ru.study.service;

import ru.study.model.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    public List<EmployeeDTO> getAllEmp(){
        return     new ArrayList<EmployeeDTO>(){
            {
                add(new EmployeeDTO(1L, "Иван", "Иванов", "Java developer"));
                add(new EmployeeDTO(2L, "Петр", "Петров", "Java developer"));
                add(new EmployeeDTO(3L, "Игорь", "Казанцев", "Java developer"));
                add(new EmployeeDTO(4L, "Сергей", "Кузнецов", "Java developer"));
                add(new EmployeeDTO(5L, "Александр", "Александров", "Java developer"));
            }};
    }
}

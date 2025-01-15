package com.example.DynamicEmployeeManagementSystem.controller;

import com.example.DynamicEmployeeManagementSystem.model.Employee;
import com.example.DynamicEmployeeManagementSystem.repository.EmployeeRepository;
import com.example.DynamicEmployeeManagementSystem.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> fetchAllEmployee() {
        return employeeService.allEmployee();
    }
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }
}

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
    @GetMapping("/page")
    public List<Employee> fetchPageByEmployee(@RequestParam Long pageNo,@RequestParam Long pageSize) {
        return (List<Employee>) employeeService.paginationEmployee(pageNo,pageSize);
    }

    @GetMapping("/special-employee")
    public List<Employee> specialEmployee() {
        return employeeService.specialEmployee();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping("/{employeeId}")
    public Employee findEmployee(@PathVariable Long employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long employeeId) {
        //System.out.println(employee);
        return employeeService.updateEmployeeById(employee, employeeId);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable Long employeeId) {

        return employeeService.deleteEmployee(employeeId);
    }


}

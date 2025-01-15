package com.example.DynamicEmployeeManagementSystem.service;

import com.example.DynamicEmployeeManagementSystem.error.AlreadyRegistered;
import com.example.DynamicEmployeeManagementSystem.model.Employee;
import com.example.DynamicEmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    public List<Employee> allEmployee() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        boolean exit=employeeRepository.existsByNameAndDepartmentAndSalary(employee.getName(),employee.getDepartment());
        if(!exit){
          return   employeeRepository.save(employee);
        }
        throw new AlreadyRegistered("Employee already added !!!");
    }
}

package com.example.DynamicEmployeeManagementSystem.service;

import com.example.DynamicEmployeeManagementSystem.error.AlreadyRegistered;
import com.example.DynamicEmployeeManagementSystem.error.NoSuchElementException;
import com.example.DynamicEmployeeManagementSystem.model.Employee;
import com.example.DynamicEmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Employee findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(()->new NoSuchElementException("Not found employee by Id "+employeeId));
    }

    public Employee updateEmployeeById(Employee employee,Long employeeId) {
        Employee emp=employeeRepository.findById(employeeId).orElse(null);
        if(emp==null)throw new NoSuchElementException("Employee not found ");
        if (employee.getName() != null) {
            emp.setName(employee.getName());
        }
        if (employee.getDesignation() != null) {
            emp.setDesignation(employee.getDesignation());
        }
        if (employee.getDepartment() != null) {
            emp.setDepartment(employee.getDepartment());
        }
        if (employee.getDateOfJoining() != null) {
            emp.setDateOfJoining(employee.getDateOfJoining());
        }
        if (employee.getSalary() != 0.0) {
            emp.setSalary(employee.getSalary());
        }
        if (employee.getAdditionalAttributes() != null) {
            emp.setAdditionalAttributes(employee.getAdditionalAttributes());
        }


        return employeeRepository.save(emp);
    }

    public String deleteEmployee(Long employeeId) {
        if(employeeRepository.existsById(employeeId)){
            employeeRepository.deleteById(employeeId);
            return "Delete success !!";
        }
        else{
            throw new NoSuchElementException("Employee not found");
        }
    }

    public List<Employee> specialEmployee() {
        return employeeRepository.findEmployeesWithMaxOrMinSalary();
    }
    public List<Employee> paginationEmployee(Long pageNo, Long pageSize) {
        Pageable pageable = PageRequest.of(pageNo.intValue(), pageSize.intValue(), Sort.by("salary").descending().and(Sort.by("name")));

        Page<Employee> employeePage=  employeeRepository.findAll( pageable);
        return employeePage.getContent();

    }
}

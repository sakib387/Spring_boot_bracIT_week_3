package com.example.DynamicEmployeeManagementSystem.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDate;
import java.util.Map;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String name;
    private String designation;
    private String department;

    private LocalDate dateOfJoining;

    private double salary;

    @Type(JsonType.class) // Use Hibernate Types to handle JSON serialization
    @Column(columnDefinition = "json") // Define as a JSON column in the database
    private AdditionalAttribute additionalAttributes;

    public Employee() {
    }

    public Employee(Long employeeId, String name, String designation, String department, LocalDate dateOfJoining, double salary, AdditionalAttribute additionalAttributes) {
        this.employeeId = employeeId;
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
        this.additionalAttributes = additionalAttributes;
    }


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public AdditionalAttribute getAdditionalAttributes() {
        return additionalAttributes;
    }

    public void setAdditionalAttributes(AdditionalAttribute additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
    }
}

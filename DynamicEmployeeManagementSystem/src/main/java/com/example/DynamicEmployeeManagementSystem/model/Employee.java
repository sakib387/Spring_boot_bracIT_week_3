package com.example.DynamicEmployeeManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String dateOfJoining;

    private double salary;

    @ManyToOne
    @JoinColumn(name = "fk_add")
    private Address address;

    @Type(JsonType.class) // Use Hibernate Types to handle JSON serialization
    @Column(columnDefinition = "json") // Define as a JSON column in the database
    private Map<String,Object> additionalAttributes;


    public Employee() {
    }


    public Employee(Long employeeId, String name, String designation, String department, String dateOfJoining, double salary, Address address, Map<String,Object> additionalAttributes) {
        this.employeeId = employeeId;
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
        this.address = address;
        this.additionalAttributes = additionalAttributes;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Map<String,Object> getAdditionalAttributes() {
        return additionalAttributes;
    }

    public void setAdditionalAttributes(Map<String,Object> additionalAttributes) {
        this.additionalAttributes = additionalAttributes;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", department='" + department + '\'' +
                ", dateOfJoining='" + dateOfJoining + '\'' +
                ", salary=" + salary +
                ", address=" + address +
                ", additionalAttributes=" + additionalAttributes +
                '}';
    }
}

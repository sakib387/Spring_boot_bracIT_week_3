package com.example.DynamicEmployeeManagementSystem.repository;

import com.example.DynamicEmployeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.name = :name AND e.department = :department ")

    boolean existsByNameAndDepartmentAndSalary(@Param("name") String name,
                                               @Param("department") String department );

    @Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(emp.salary) FROM Employee emp) " +
            "OR e.salary = (SELECT MIN(emp.salary) FROM Employee emp) order by e.salary")
    List<Employee> findEmployeesWithMaxOrMinSalary();

}

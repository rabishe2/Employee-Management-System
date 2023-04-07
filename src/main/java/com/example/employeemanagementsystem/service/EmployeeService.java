package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    String addEmployee(Employee employee);

    Employee updateEmployee(long id, Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(long id);


    Optional<Employee> deleteEmployee(long id);
}

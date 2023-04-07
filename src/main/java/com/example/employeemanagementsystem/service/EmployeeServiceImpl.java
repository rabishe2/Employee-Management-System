package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.exception.EmployeeNotFoundException;
import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.repo.EmployeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeRepo employeRepo;
    @Override
    public String addEmployee(Employee employee) {
        Employee employeeFromDB =  employeRepo.save(employee);
        return "Inserted successfully in DB. and ID generated for this is: "+employeeFromDB.getEmployeeid();
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) throws EmployeeNotFoundException{
        Employee employeeFromDb = employeRepo.findById(id).orElseThrow(()->new EmployeeNotFoundException("EMPLOYEE_NOT_FOUND"));
        employeeFromDb.setFirstname(employee.getFirstname());
        employeeFromDb.setLastname(employee.getLastname());
        employeeFromDb.setDepartment(employee.getDepartment());
        employeeFromDb.setDesignation(employee.getDesignation());
        employeeFromDb.setSalary(employee.getSalary());
        employeRepo.save(employeeFromDb);
        return employeeFromDb;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) throws EmployeeNotFoundException {
        return employeRepo.findById(id).orElseThrow(()->new EmployeeNotFoundException("EMPLOYEE_NOT_FOUND"));
    }


    @Override
    public Optional<Employee> deleteEmployee(long id) throws EmployeeNotFoundException{
        Employee employeeFromDB = employeRepo.findById(id).orElseThrow(()->new EmployeeNotFoundException("EMPLOYEE_NOT_FOUND"));
        employeRepo.delete(employeeFromDB);
        return Optional.of(employeeFromDB);
    }

}

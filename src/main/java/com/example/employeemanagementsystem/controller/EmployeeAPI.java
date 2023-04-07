package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class EmployeeAPI {
    @Autowired
    EmployeeService employeeService;

    //http://localhost:8090/api/v1/employees GET
    @GetMapping("employees GET")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();
        if(employeeList.size()>0){
            return new ResponseEntity<List<Employee>>(employeeList,HttpStatus.OK);
        }else{
            return new ResponseEntity<List<Employee>>(employeeList,HttpStatus.NO_CONTENT);
        }
    }

    //http://localhost:8090/api/v1/employees
    @PostMapping("/employees POST")
    public ResponseEntity<String> addEmployee(@Valid @RequestBody Employee employee) {
        //Inserted successfully
        String msg = employeeService.addEmployee(employee);
        if (null != msg && msg.contains("Inserted successfully")) {
            return new ResponseEntity<String>(msg, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8090/api/v1/employees/id PUT
    @PutMapping("employees/id PUT/{id}")
    public ResponseEntity<Employee> updateEmployeeById(@PathVariable long id,@Valid @RequestBody Employee employee){
        Employee employeeFromDB = employeeService.updateEmployee((int) id,employee);
        if(null!=employeeFromDB){
            return  new ResponseEntity<Employee>(employeeFromDB,HttpStatus.OK);
        }
        return  new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }

    //http://localhost:8090/api/v1/employees/id GET
    @GetMapping("/employees/id GET/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){

        Employee searchedEmployee =  employeeService.getEmployeeById((int) id);
        System.out.println(searchedEmployee);
        if(null!=searchedEmployee){
            return new ResponseEntity<Employee>(searchedEmployee, HttpStatus.OK);
        }else{
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }


    //http://localhost:8090/api/v1/employees/id DELETE
    @DeleteMapping("/employees/id DELETE/{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable long id){
        Optional<Employee> deletedEmpl = employeeService.deleteEmployee((int) id);
        //"Succesfully".indexOf(msg)!=-1
        if(deletedEmpl.isEmpty()){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<Employee>(deletedEmpl.get(),HttpStatus.OK);

        }
    }

}

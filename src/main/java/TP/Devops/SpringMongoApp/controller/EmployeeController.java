package TP.Devops.SpringMongoApp.controller;

import TP.Devops.SpringMongoApp.model.Employee;
import TP.Devops.SpringMongoApp.service.EmployeeService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = {"http://www.spring-mongo.xyz","http://spring-mongo.xyz"})
@Timed

public class EmployeeController {
    @Autowired
    private  EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/all")
    @Timed(value = "employee.getall.timer", description = "Time taken to get all  employees")

    public ResponseEntity<List<Employee>> getAllEmployees () {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @Timed(value = "employee.find.timer", description = "Time taken to find an employee")

    public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") String id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @Timed(value = "employee.add.timer", description = "Time taken to add an employee")

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @Timed(value = "employee.update.timer", description = "Time taken to update an employee")

    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Timed(value = "employee.delete.timer", description = "Time taken to delete an employee")

    public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
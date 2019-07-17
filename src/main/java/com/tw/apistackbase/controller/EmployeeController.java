package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> list() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        return employeeService.findById(employeeId);
    }

    @GetMapping(value = "/employees", params = {"page", "pageSize"})
    public List<Employee> getByPageAndSize(@RequestParam(value = "page") int page, @RequestParam(value = "pageSize") int pageSize) {
        return employeeService.findByPageAndSize(page, pageSize);
    }

    @GetMapping(value = "/employees", params = {"gender"})
    public List<Employee> getByGender(@RequestParam String gender) {
        return employeeService.findByGender(gender);
    }

    @RequestMapping(value = "/employees", method = {RequestMethod.POST, RequestMethod.PUT})
    public Employee updateOrSave(@RequestBody Employee employee) {
        return employeeService.updateOrSave(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void getByGender(@PathVariable int employeeId) {
        employeeService.deleteById(employeeId);
    }
}

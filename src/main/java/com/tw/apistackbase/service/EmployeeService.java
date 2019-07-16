package com.tw.apistackbase.service;

import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findById(int employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public List<Employee> findByPageAndSize(int page, int pageSize) {
        return employeeRepository.findByPageAndSize(page,pageSize);
    }

    public List<Employee> findByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public Employee updateOrSave(Employee employee) {
        return employeeRepository.updateOrSave(employee);
    }

    public void deleteById(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}

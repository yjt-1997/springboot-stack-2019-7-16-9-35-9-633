package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {
    private static Map<Integer, Employee> employees = new HashMap<>();

    static {
        employees.put(4, new Employee(4, "alibaba1", 20, "male", 6000));
        employees.put(11, new Employee(11, "tengxun2", 19, "female", 7000));
        employees.put(6, new Employee(6, "alibaba3", 19, "male", 8000));
        employees.put(13, new Employee(4, "huiwei1", 20, "male", 8000));
    }

    public void updateOrSave(Employee employee) {

    }

    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }
}

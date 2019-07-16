package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private static Map<Integer, Employee> employees = new LinkedHashMap<>();

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

    public Employee findById(int employeeId) {
        List<Employee> filterEmployee = employees.values().stream().filter(employee -> (employee.getId() == employeeId)).collect(Collectors.toList());
        return filterEmployee == null ? null : filterEmployee.get(0);
    }

    public List<Employee> findByPageAndSize(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        int end = page * pageSize;
        List<Employee> result = new ArrayList<>();
        List<Employee> employeesValues = findAll();
        for (int i = start; i < end; i++) {
            if (employeesValues.get(i) == null) {
                return result;
            }
            result.add(employeesValues.get(i));
        }
        return result;
    }
}

package com.tw.apistackbase.service;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findByName(String companyName) {
        return companyRepository.findByName(companyName);
    }

    public List<Company> findByPageAndSize(int page, int pageSize) {
        return null;
    }

    public List<Company> findByGender(String gender) {
        return null;
    }

    public Company updateOrSave(Company company) {
        return null;
    }

    public void deleteById(int companyId) {
    }

    public List<Employee> findEmployeesByName(String companyName) {
        return null;
    }
}

package com.tw.apistackbase.repository;

import com.tw.apistackbase.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class CompanyRepository {
    private static HashMap<String, Company> companies = new HashMap<>();

    static {
        companies.put("Test1", new Company("alibaba1", 100));
        companies.put("Test2", new Company("tengxun2", 90));
    }

    public List<Company> findAll() {
        return new ArrayList<>(companies.values());
    }

    public Company findByName(String companyName) {
        return findAll().stream().filter(company -> company.getCompanyName().equals(companyName)).findFirst().get();
    }
}

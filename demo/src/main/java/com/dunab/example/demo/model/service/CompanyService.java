package com.dunab.example.demo.model.service;

import com.dunab.example.demo.api.dto.CompanyDTO;
import com.dunab.example.demo.model.entity.Company;
import com.dunab.example.demo.model.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepo;

    public Company add(CompanyDTO dto){
        Company company = new Company();
        company.setName(dto.getName());
        Company parent = companyRepo.findById(dto.getParent().getId())
                .orElseThrow(() -> new RuntimeException("No parent company found by id: " + dto.getParent().getId()));
        company.setParent(parent);

        return companyRepo.save(company);
    }
}

package com.dunab.example.demo.model.service;

import com.dunab.example.demo.api.dto.CompanyDTO;
import com.dunab.example.demo.model.entity.Company;
import com.dunab.example.demo.model.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepo;

    public Company add(CompanyDTO dto) {
        Company company = new Company();
        company.setName(dto.getName());
        if (dto.getParent() != null) {
            Company parent = companyRepo.findById(dto.getParent())
                    .orElseThrow(() -> new RuntimeException("No parent company found by id: " + dto.getParent()));
            company.setParent(parent);
        }
        return companyRepo.save(company);
    }

    public Boolean delete(Long id) {
        Optional<Company> companyOptional = companyRepo.findById(id);
        if (companyOptional.isPresent()) {
            companyRepo.delete(companyOptional.get());
            return true;
        }
        return false;
    }

    public Company update(CompanyDTO dto) {
        Optional<Company> companyOptional = companyRepo.findById(dto.getId());
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(dto.getName());
            if (dto.getParent() != null) {
                Company parent = companyRepo.findById(dto.getParent())
                        .orElseThrow(() -> new RuntimeException("No parent company found by id: " + dto.getParent()));
                company.setParent(parent);
            }
            companyRepo.save(company);
            return company;
        }
        return new Company();
    }

    public Iterable<Company> list(){
        return companyRepo.findAll();
    }
}

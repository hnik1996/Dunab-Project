package com.dunab.example.demo.model.repository;

import com.dunab.example.demo.model.entity.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company,Long> {
}

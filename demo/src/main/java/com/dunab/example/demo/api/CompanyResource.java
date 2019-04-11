package com.dunab.example.demo.api;

import com.dunab.example.demo.api.dto.CompanyDTO;
import com.dunab.example.demo.model.entity.Company;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CompanyResource {

    @RequestMapping(value = "/add-company", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyDTO dto) {
        Company company = new Company();
        company.setName(dto.getName());
        if (dto.getParent() != null) {
            Company parent = new Company();
            parent.setName(dto.getParent().getName());
            company.setParent(parent);
        }
        Long id = company.getId();
        dto.setId(id);
        return ResponseEntity.ok(dto);
    }
}

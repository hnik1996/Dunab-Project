package com.dunab.example.demo.api;

import com.dunab.example.demo.api.dto.CompanyDTO;
import com.dunab.example.demo.model.entity.Company;
import com.dunab.example.demo.model.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CompanyResource {

    @Autowired
    private CompanyService companyService;


    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public @ResponseBody
    Company addCompany(@RequestBody CompanyDTO dto) {
        return companyService.add(dto);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Boolean delete(@PathVariable Long id) {
        return companyService.delete(id);
    }

    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    public @ResponseBody
    Company update(@RequestBody CompanyDTO dto) {
        return companyService.update(dto);
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Company> list() {
        return companyService.list();
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Company get(@PathVariable Long id) {
        Optional<Company> companyOptional = companyService.get(id);
        return companyOptional.orElseGet(Company::new);
    }
}

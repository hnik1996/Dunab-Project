package com.dunab.example.demo.api.dto;

public class CompanyDTO {

    private Long id;

    private String name;

    private CompanyDTO parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompanyDTO getParent() {
        return parent;
    }

    public void setParent(CompanyDTO parent) {
        this.parent = parent;
    }
}

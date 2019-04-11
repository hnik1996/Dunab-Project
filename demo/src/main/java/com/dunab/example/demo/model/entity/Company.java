package com.dunab.example.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Company {

    private Long id;

    private String name;

    @ManyToOne
    private Company parent;

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

    public Company getParent() {
        return parent;
    }

    public void setParent(Company parent) {
        this.parent = parent;
    }
}

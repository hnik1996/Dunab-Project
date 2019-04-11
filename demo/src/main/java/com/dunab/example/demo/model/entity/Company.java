package com.dunab.example.demo.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @ManyToOne
    private Company parent;

    public Long getId() {
        return id;
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

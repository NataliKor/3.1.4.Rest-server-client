package com.example.spring_boot_rest.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private int id;

    @Column
    private String name;

    public Role() {
    }

    public Role(String role) {
        this.name = role;
    }

    public Role(int id) {
        this.id = id;
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

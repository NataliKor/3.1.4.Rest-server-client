package com.example.spring_boot_rest.service;

import com.example.spring_boot_rest.model.Role;

import java.util.List;

public interface RoleService {
    //поиск роли по id
    Role findById(int id);

    //поиск роли по имени
    Role findByAuthority(String name);

    //выводим все roles
    List<Role> findAllBy();
}

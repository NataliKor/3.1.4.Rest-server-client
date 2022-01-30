package com.example.spring_boot_rest.repository;

import com.example.spring_boot_rest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    //поиск роли по id
    Role findById(int id);

    //поиск роли по имени
    //Role findByAuthority(String name);
    Role findByName(String name);

    //выводим все roles
    List<Role> findAllBy();
}

package com.example.spring_boot_rest.service;

import com.example.spring_boot_rest.model.Role;
import com.example.spring_boot_rest.model.User;

import java.util.List;
import java.util.Set;

public interface MyService {
    List<User> getAllUsers();
    User getUser(int id);
    void saveOrUpdateUser(User user);
    void deleteUser(int id);
    List<Role> getAllRoles();
    User getUserByUsername(String username);
    User updateSetRolesAtUser(User user, int id, Set<Role> roleSet);
}

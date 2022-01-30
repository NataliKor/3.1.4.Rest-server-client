package com.example.spring_boot_rest.service;

import com.example.spring_boot_rest.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    void saveOrUpdateUser(User user);

    User searchUser(int id);

    User searchForUserByName(String name);

    void deleteUserById(int id);
}

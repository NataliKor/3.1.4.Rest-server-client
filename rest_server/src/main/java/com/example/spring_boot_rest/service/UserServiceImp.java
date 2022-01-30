package com.example.spring_boot_rest.service;

import com.example.spring_boot_rest.model.Role;
import com.example.spring_boot_rest.model.User;
import com.example.spring_boot_rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAllBy();
    }

    @Override
    @Transactional
    public void saveOrUpdateUser(User user) {
        Set<Role> roleList = user.getRoles();
        for (Role role: roleList) {
            if (role.getId() == 0) {
                if (role.getName().equals("ADMIN")) {
                    role.setId(1);
                }
                if (role.getName().equals("USER")) {
                    role.setId(2);
                }
            }
        }
        user.setRoles(roleList);
        User userFromBD = userRepository.findById(user.getId());
        if (userFromBD == null) {
            System.out.println("Новый пользователь сохранен в БД.");
        } else {
            System.out.println("Пользователь обновлен в БД.");
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User searchUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User searchForUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}

package com.example.spring_boot_rest.controller;

import com.example.spring_boot_rest.model.Role;
import com.example.spring_boot_rest.model.User;
import com.example.spring_boot_rest.service.RoleService;
import com.example.spring_boot_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public List<Role> showAllRoles() {
        return roleService.findAllBy();
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.getAllUser();
    }

    //поиск пользователя по id
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return userService.searchUser(id);
    }

    //сохраняем нового пользователя в БД
    @PostMapping("/users")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
        String str = "Пользователь сохранен";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    //обновляем пользователя в БД
    @PutMapping("/users")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
        String str = "Пользователь обновлен";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    //удаляем пользователя из БД
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        String str = "Пользователь c ID " + id + " удален";
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    //поиск пользователя по username
    @GetMapping("/users/getUser/{username}")
    public User getUserByUsername (@PathVariable String username) {
        return userService.searchForUserByName(username);
    }
}

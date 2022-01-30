package com.example.spring_boot_rest.controller;

import com.example.spring_boot_rest.model.Role;
import com.example.spring_boot_rest.model.User;
import com.example.spring_boot_rest.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
public class AdminController {
    @Autowired
    MyService myService;

    //вывод всех польхователь из БД в представление
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", myService.getAllUsers());
        return "admin/index";
    }

    //передаем пустого пользователя на форму
    @GetMapping("/new")
    public String newUser(Model model) {
        User user = new User();
        user.setRoles(new HashSet<>(myService.getAllRoles()));
        model.addAttribute("user", user);
        return "admin/new";
    }

    //добавляем пользователя, переданного с формы, в БД
    @PostMapping()
    public String add(@ModelAttribute("user") User user) {
        myService.saveOrUpdateUser(user);
        return "redirect:/";
    }

    //возвращает в форму пользователя по id
    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", myService.getUser(id));
        model.addAttribute("roles", myService.getAllRoles());
        return "admin/edit";
    }

    //принимаем из формы нового пользователя и обновляем его по id в БД
    @PostMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") int id, @ModelAttribute("roles") HashSet<Role> roleSet) {
        User user2 = myService.updateSetRolesAtUser(user, id,roleSet);
        myService.saveOrUpdateUser(user2);
        return "redirect:/";
    }

    //удаляем из БД пользователя по id
    @RequestMapping(value = "/delete/{id}", produces = "application/json", method = {RequestMethod.POST, RequestMethod.DELETE})
    public String delete(@PathVariable("id") int id) {
        myService.deleteUser(id);
        return "redirect:/";
    }
}

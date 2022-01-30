package com.example.spring_boot_rest.service;

import com.example.spring_boot_rest.model.Role;
import com.example.spring_boot_rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
public class MyServiceImp implements MyService, UserDetailsService {
    @Autowired
    RestTemplate restTemplate;

    private final static String URL = "http://localhost:8080/api/users";
    private final static String URL_ROLES = "http://localhost:8080/api/roles";

    //метод посылает http-запрос и получает от нашего rest-server список всех пользователей
    @Override
    public List<User> getAllUsers() {
        //этим кодом мы отправляем запрос и его результат получаем в responseEntity
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {
                });

        //из тела responseEntity получаем полезную нагрузку (List<User>)
        return responseEntity.getBody();
    }

    //метод получает от нашего rest-server конкретного пользователя по id
    @Override
    public User getUser(int id) {
        return restTemplate.getForObject(URL + "/" + id, User.class);
    }

    //метод сохраняет нового пользователя или обновляет уже существующего
    @Override
    public void saveOrUpdateUser(User user) {
        int id = user.getId();

        if (id == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, user,
                    String.class);
            System.out.println("Новый пользователь добавлен в БД.");
        } else {
            restTemplate.put(URL, user);
            System.out.println("Пользователь с id " + id + " обновлен.");
        }
    }

    //метод удаляет пользователя по его id
    @Override
    public void deleteUser(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("Пользователь с id " + id + " удален из БД.");
    }

    //метод посылает http-запрос и получает от нашего rest-server список всех ролей
    @Override
    public List<Role> getAllRoles() {
        //этим кодом мы отправляем запрос и его результат получаем в responseEntity
        ResponseEntity<List<Role>> responseEntity = restTemplate.exchange(URL_ROLES, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Role>>() {
                });

        //из тела responseEntity получаем полезную нагрузку (List<Role>)
        return responseEntity.getBody();
    }

    //метод получает от нашего rest-server конкретного пользователя по username
    @Override
    public User getUserByUsername(String username) {
        return restTemplate.getForObject(URL + "/getUser/" + username, User.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = restTemplate.getForObject(URL + "/getUser/" + username, User.class);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    //метод обновляет у пользователя роли
    @Override
    public User updateSetRolesAtUser(User user, int id, Set<Role> roleSet) {
        if (roleSet.size() != 0) {
            user.setRoles(roleSet);
        } else {
            user.setRoles(getUser(id).getRoles());
        }
        return user;
    }
}

package com.example.groupsSystem.controllers.user;

import com.example.groupsSystem.models.user.User;
import com.example.groupsSystem.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<User> findAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User findUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public int countUsers() {
        return service.getNumberOfUsers();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public User deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }
}
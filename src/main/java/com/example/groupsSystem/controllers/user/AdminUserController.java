package com.example.groupsSystem.controllers.user;

import com.example.groupsSystem.models.user.AdminUser;
import com.example.groupsSystem.services.user.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RestController
@RequestMapping("/adminUsers")
public class AdminUserController {

    @Autowired
    private AdminUserService service;

    @PostMapping
    public AdminUser addAdminUser(@RequestBody AdminUser adminUser) {
        return service.addAdminUser(adminUser);
    }

    @GetMapping
    public List<AdminUser> findAllAdminUsers() {
        return service.getAdminUsers();
    }

    @GetMapping("/{id}")
    public AdminUser findAdminUserById(@PathVariable int id) {
        return service.getAdminUserById(id);
    }

    @GetMapping("/count")
    public int countAdminUsers() {
        return service.getNumberOfAdminUsers();
    }

    @PutMapping
    public AdminUser updateUser(@RequestBody AdminUser adminUser) {
        return service.updateAdminUser(adminUser);
    }

    @DeleteMapping("/{id}")
    public AdminUser deleteAdminUser(@PathVariable int id) {
        return service.deleteAdminUser(id);
    }


}

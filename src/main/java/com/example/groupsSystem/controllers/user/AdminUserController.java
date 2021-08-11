package com.example.groupsSystem.controllers.user;

import com.example.groupsSystem.models.group.GroupOfUsers;
import com.example.groupsSystem.models.post.PublicPost;
import com.example.groupsSystem.models.requests.RequestForCreateGroup;
import com.example.groupsSystem.models.requests.RequestForPublicPost;
import com.example.groupsSystem.models.user.AdminUser;
import com.example.groupsSystem.repositories.post.PublicPostRepository;
import com.example.groupsSystem.repositories.requests.RequestForCreateGroupRepository;
import com.example.groupsSystem.repositories.requests.RequestForPublicPostRepository;
import com.example.groupsSystem.services.user.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AdminUser addAdminUser(@RequestBody AdminUser adminUser) {
        return service.addAdminUser(adminUser);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<AdminUser> findAllAdminUsers() {
        return service.getAdminUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AdminUser findAdminUserById(@PathVariable int id) {
        return service.getAdminUserById(id);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public int countAdminUsers() {
        return service.getNumberOfAdminUsers();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")

    public AdminUser updateUser(@RequestBody AdminUser adminUser) {
        return service.updateAdminUser(adminUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AdminUser deleteAdminUser(@PathVariable int id) {
        return service.deleteAdminUser(id);
    }

    @GetMapping("/acceptGroup/{idOfRequest}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void acceptGroup(@PathVariable int idOfRequest) {
        service.acceptGroup(idOfRequest);
    }

    @GetMapping("/acceptPublicPost/{idOfRequest}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void acceptPublicPost(@PathVariable int idOfRequest) {
        service.acceptPublicPost(idOfRequest);
    }
}

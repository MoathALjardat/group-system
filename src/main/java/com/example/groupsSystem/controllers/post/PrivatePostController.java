package com.example.groupsSystem.controllers.post;

import com.example.groupsSystem.models.post.PrivatePost;
import com.example.groupsSystem.services.post.PrivatePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/privatePosts")
@Controller
@RestController
public class PrivatePostController {

    @Autowired
    private PrivatePostService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public PrivatePost addPrivatePost(@RequestBody PrivatePost privatePost) {
        return service.addPrivatePost(privatePost);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<PrivatePost> findAllPrivatePosts() {
        return service.getPrivatePosts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public PrivatePost findPrivatePostById(@PathVariable int id) {
        return service.getPrivatePostById(id);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public int countPrivatePosts() {
        return service.getNumberOfPrivatePosts();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public PrivatePost updatePrivatePost(@RequestBody PrivatePost privatePost) {
        return service.updatePrivatePost(privatePost);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public PrivatePost deletePrivatePost(@PathVariable int id) {
        return service.deletePrivatePost(id);
    }

}

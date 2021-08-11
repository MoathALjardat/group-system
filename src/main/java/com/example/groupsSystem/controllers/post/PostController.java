package com.example.groupsSystem.controllers.post;

import com.example.groupsSystem.models.post.Post;
import com.example.groupsSystem.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RestController
@RequestMapping("/posts")
public class PostController {


    @Autowired
    private PostService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Post addPost(@RequestBody Post post) {
        return service.addPost(post);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Post> findAllPosts() {
        return service.getPosts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Post findPostById(@PathVariable int id) {
        return service.getPostById(id);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public int countPosts() {
        return service.getNumberOfPosts();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Post updatePost(@RequestBody Post post) {
        return service.updatePost(post);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Post deletePost(@PathVariable int id) {
        return service.deletePost(id);
    }
}

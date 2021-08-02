package com.example.groupsSystem.controllers.post;

import com.example.groupsSystem.models.post.Post;
import com.example.groupsSystem.services.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Post addPost(@RequestBody Post post) {
        return service.addPost(post);
    }

    @GetMapping
    public List<Post> findAllPosts() {
        return service.getPosts();
    }

    @GetMapping("/{id}")
    public Post findPostById(@PathVariable int id) {
        return service.getPostById(id);
    }

    @GetMapping("/count")
    public int countPosts() {
        return service.getNumberOfPosts();
    }

    @PutMapping
    public Post updatePost(@RequestBody Post post) {
        return service.updatePost(post);
    }

    @DeleteMapping("/{id}")
    public Post deletePost(@PathVariable int id) {
        return service.deletePost(id);
    }
}

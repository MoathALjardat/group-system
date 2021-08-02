package com.example.groupsSystem.controllers.post;

import com.example.groupsSystem.models.post.PublicPost;
import com.example.groupsSystem.services.post.PublicPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RestController
@RequestMapping("/publicPosts")
public class PublicPostController {

    @Autowired
    private PublicPostService service;
    @PostMapping
    public PublicPost addPublicPost(@RequestBody PublicPost publicPost) {
        return service.addPublicPost(publicPost);
    }

    @GetMapping
    public List<PublicPost> findAllPublicPosts() {
        return service.getPublicPosts();
    }

    @GetMapping("/{id}")
    public PublicPost findPublicPostById(@PathVariable int id) {
        return service.getPublicPostById(id);
    }

    @GetMapping("/count")
    public int countPublicPosts() {
        return service.getNumberOfPublicPosts();
    }

    @PutMapping
    public PublicPost updatePublicPost(@RequestBody PublicPost publicPost) {
        return service.updatePublicPost(publicPost);
    }

    @DeleteMapping("/{id}")
    public PublicPost deletePublicPost(@PathVariable int id) {
        return service.deletePublicPost(id);
    }

}

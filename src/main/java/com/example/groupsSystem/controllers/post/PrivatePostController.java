package com.example.groupsSystem.controllers.post;

import com.example.groupsSystem.models.post.PrivatePost;
import com.example.groupsSystem.services.post.PrivatePostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PrivatePost addPrivatePost(@RequestBody PrivatePost privatePost) {
        return service.addPrivatePost(privatePost);
    }

    @GetMapping
    public List<PrivatePost> findAllPrivatePosts() {
        return service.getPrivatePosts();
    }

    @GetMapping("/{id}")
    public PrivatePost findPrivatePostById(@PathVariable int id) {
        return service.getPrivatePostById(id);
    }

    @GetMapping("/count")
    public int countPrivatePosts() {
        return service.getNumberOfPrivatePosts();
    }

    @PutMapping
    public PrivatePost updatePrivatePost(@RequestBody PrivatePost privatePost) {
        return service.updatePrivatePost(privatePost);
    }

    @DeleteMapping("/{id}")
    public PrivatePost deletePrivatePost(@PathVariable int id) {
        return service.deletePrivatePost(id);
    }

}

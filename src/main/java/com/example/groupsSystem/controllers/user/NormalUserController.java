package com.example.groupsSystem.controllers.user;

import com.example.groupsSystem.models.post.PublicPost;
import com.example.groupsSystem.models.user.NormalUser;
import com.example.groupsSystem.services.user.NormalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RestController
@RequestMapping("/normalUsers")
public class NormalUserController {

    @Autowired
    private NormalUserService service;

    @PostMapping
    public NormalUser addNormalUser(@RequestBody NormalUser normalUser) {
        return service.addNormalUser(normalUser);
    }

    @GetMapping
    public List<NormalUser> findAllNormalUsers() {
        return service.getNormalUsers();
    }

    @GetMapping("/{id}")
    public NormalUser findNormalUserById(@PathVariable int id) {
        return service.getNormalUserById(id);
    }

    @GetMapping("/count")
    public int countNormalUsers() {
        return service.getNumberOfNormalUsers();
    }

    @PutMapping
    public NormalUser updateUser(@RequestBody NormalUser normalUser) {
        return service.updateNormalUser(normalUser);
    }

    @DeleteMapping("/{id}")
    public NormalUser deleteNormalUser(@PathVariable int id) {
        return service.deleteNormalUser(id);
    }

  /*  @PostMapping("/addPublicPost")
    public void addPublicPost(@RequestBody PublicPost publicPost)
    {
        service.requestForPublicPost(publicPost);
    }

    */
}

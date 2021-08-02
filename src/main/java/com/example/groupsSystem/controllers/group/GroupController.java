package com.example.groupsSystem.controllers.group;

import com.example.groupsSystem.models.group.GroupOfUsers;
import com.example.groupsSystem.services.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/groups")
public class GroupController {


    @Autowired
    private GroupService service;

    @PostMapping
    public GroupOfUsers addGroup(@RequestBody GroupOfUsers post) {
        return service.addGroup(post);
    }

    @GetMapping
    public List<GroupOfUsers> findAllGroups() {
        return service.getGroups();
    }

    @GetMapping("/{id}")
    public GroupOfUsers findGroupById(@PathVariable int id) {
        return service.getGroupById(id);
    }

    @GetMapping("/count")
    public int countGroups() {
        return service.getNumberOfGroups();
    }

    @PutMapping
    public GroupOfUsers updateGroup(@RequestBody GroupOfUsers post) {
        return service.updateGroup(post);
    }

    @DeleteMapping("/{id}")
    public GroupOfUsers deleteGroup(@PathVariable int id) {
        return service.deleteGroup(id);
    }
}

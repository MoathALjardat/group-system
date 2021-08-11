package com.example.groupsSystem.controllers.user;

import com.example.groupsSystem.models.comment.Comment;
import com.example.groupsSystem.models.post.PrivatePost;
import com.example.groupsSystem.models.post.PublicPost;
import com.example.groupsSystem.models.user.NormalUser;
import com.example.groupsSystem.services.user.NormalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public NormalUser addNormalUser(@RequestBody NormalUser normalUser) {
        return service.addNormalUser(normalUser);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<NormalUser> findAllNormalUsers() {
        return service.getNormalUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public NormalUser findNormalUserById(@PathVariable int id) {
        return service.getNormalUserById(id);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public int countNormalUsers() {
        return service.getNumberOfNormalUsers();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public NormalUser updateUser(@RequestBody NormalUser normalUser) {
        return service.updateNormalUser(normalUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public NormalUser deleteNormalUser(@PathVariable int id) {
        return service.deleteNormalUser(id);
    }

    @GetMapping("/createGroup")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    public void requestForCreatingGroupFromManger() {
        service.requestForCreatingGroupFromManger();
    }

    @PostMapping("/requestForPublicPost")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    public void requestForPublicPost(@RequestBody PublicPost publicPost) {
        service.requestForPublicPost(publicPost);
    }

    @PostMapping("/requestForPrivatePost/{groupOfUsersId}")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    public void requestForPrivatePostInGroup(@RequestBody PrivatePost privatePost, @PathVariable int groupOfUsersId) {
        service.requestForPrivatePostInGroup(privatePost, groupOfUsersId);
    }


    @GetMapping("/acceptPrivatePost/{groupId}/{privatePostId}")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    public void acceptPrivatePost(@PathVariable int privatePostId, @PathVariable int groupId) {
        service.acceptPrivatePost(privatePostId, groupId);
    }

    @GetMapping("/requestForJoinGroupFromGroupAdmin/{groupOfUsersId}")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    public void requestForJoinGroupFromGroupAdmin(@PathVariable int groupOfUsersId) {
        service.requestForJoinGroupFromGroupAdmin(groupOfUsersId);
    }

    @GetMapping("/acceptToJoinGroup/{groupOfUsersId}/{normalUserId}")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    public void acceptToJoinGroup(@PathVariable int groupOfUsersId, @PathVariable int normalUserId) {
    }

    @PostMapping("/commentInPost/{postId}")
    @PreAuthorize("hasAnyRole('ROLE_NORMAL')")
    public void commentInPost(@PathVariable int postId, @RequestBody Comment comment) {
        service.commentInPost(postId, comment);
    }

}



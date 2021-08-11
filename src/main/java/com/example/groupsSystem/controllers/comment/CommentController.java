package com.example.groupsSystem.controllers.comment;

import com.example.groupsSystem.models.comment.Comment;
import com.example.groupsSystem.services.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Comment addComment(@RequestBody Comment comment) {
        return service.addComment(comment);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<Comment> findAllComments() {
        return service.getComments();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Comment findCommentById(@PathVariable int id) {
        return service.getCommentById(id);
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public int countComments() {
        return service.getNumberOfComments();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Comment updateComment(@RequestBody Comment comment) {
        return service.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Comment deleteComment(@PathVariable int id) {
        return service.deleteComment(id);
    }
}

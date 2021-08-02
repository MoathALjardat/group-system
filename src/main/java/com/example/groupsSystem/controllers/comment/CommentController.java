package com.example.groupsSystem.controllers.comment;

import com.example.groupsSystem.models.comment.Comment;
import com.example.groupsSystem.services.comment.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Comment addComment(@RequestBody Comment comment) {
        return service.addComment(comment);
    }

    @GetMapping
    public List<Comment> findAllComments() {
        return service.getComments();
    }

    @GetMapping("/{id}")
    public Comment findCommentById(@PathVariable int id) {
        return service.getCommentById(id);
    }

    @GetMapping("/count")
    public int countComments() {
        return service.getNumberOfComments();
    }

    @PutMapping
    public Comment updateComment(@RequestBody Comment comment) {
        return service.updateComment(comment);
    }

    @DeleteMapping("/{id}")
    public Comment deleteComment(@PathVariable int id) {
        return service.deleteComment(id);
    }
}

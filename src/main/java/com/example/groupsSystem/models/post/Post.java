package com.example.groupsSystem.models.post;

import com.example.groupsSystem.models.comment.Comment;
import com.example.groupsSystem.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Post {

    public Post() {
    }

    public Post(String tittle, String body, User writer, List<Comment> commentList , boolean accepted) {
        this.tittle = tittle;
        this.body = body;
        this.writer = writer;
        this.commentList = commentList;
        this.accepted = accepted ;
    }

    @Id
    @GeneratedValue
    private int id ;

    private String tittle ;
    private String body ;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User writer;

    @OneToMany
    @JsonIgnore
    private List<Comment> commentList ;

    private boolean accepted ;
}
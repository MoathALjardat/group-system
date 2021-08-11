package com.example.groupsSystem.models.comment;

import com.example.groupsSystem.models.post.Post;
import com.example.groupsSystem.models.user.NormalUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(
            cascade = CascadeType.ALL)
    @JoinColumn
    private Post post;

    private String body;

    private boolean accepted;

    @ManyToOne(
            cascade = CascadeType.ALL)
    @JoinColumn
    private NormalUser writter;

    public Comment() {
    }

    public Comment(Post post, String body) {
        this.post = post;
        this.body = body;
    }
}

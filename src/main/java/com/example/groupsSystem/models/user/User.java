package com.example.groupsSystem.models.user;


import com.example.groupsSystem.models.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode
public abstract class User {

    public User(String userName, String password, List<Post> posts) {
        this.userName = userName;
        this.password = password;
        this.posts = posts;
    }

    public User() {
    }

    @Id
    @GeneratedValue
    private int id;

    private String userName;
    private String password;

    @OneToMany
    @JsonIgnore
    private List<Post> posts;
}

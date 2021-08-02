package com.example.groupsSystem.models.requests;

import com.example.groupsSystem.models.post.Post;
import com.example.groupsSystem.models.post.PublicPost;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity

public class RequestForPublicPost {
    @Id
    @GeneratedValue
    int id ;

    @ManyToOne
    @JoinColumn
    PublicPost publicPost  ;
}
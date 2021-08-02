package com.example.groupsSystem.models.post;

import com.example.groupsSystem.models.comment.Comment;
import com.example.groupsSystem.models.group.GroupOfUsers;
import com.example.groupsSystem.models.user.NormalUser;
import com.example.groupsSystem.models.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class PrivatePost extends Post{

    @ManyToOne
    @JoinColumn
    private GroupOfUsers group ;


    @ManyToOne
    @JoinColumn
    private NormalUser normalUserAcceptThisPost ;

    public PrivatePost() {
    }

    public PrivatePost(String tittle, String body, User writer, List<Comment> commentList , boolean accepted) {
        super(tittle, body, writer, commentList , accepted);
    }
    public PrivatePost(GroupOfUsers group, NormalUser normalUserAcceptThisPost) {
        this.group = group;
        this.normalUserAcceptThisPost = normalUserAcceptThisPost;
    }

    public PrivatePost(String tittle, String body, User writer, List<Comment> commentList, GroupOfUsers group, NormalUser normalUserAcceptThisPost , boolean accepted) {
        super(tittle, body, writer, commentList,accepted);
        this.group = group;
        this.normalUserAcceptThisPost = normalUserAcceptThisPost;
    }


}

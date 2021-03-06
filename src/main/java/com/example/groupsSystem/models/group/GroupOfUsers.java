package com.example.groupsSystem.models.group;

import com.example.groupsSystem.models.post.PrivatePost;
import com.example.groupsSystem.models.user.NormalUser;
import com.example.groupsSystem.models.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter

@Entity
public class GroupOfUsers {

    @Id
    @GeneratedValue
    private int id;

    private boolean accepted;


    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JsonIgnore
    private List<NormalUser> users;

    @OneToMany(
            orphanRemoval = true,
            cascade = CascadeType.ALL)

    @JsonIgnore
    private List<PrivatePost> privatePosts;

    @ManyToOne(
            cascade = CascadeType.ALL)
    @JoinColumn
    private NormalUser groupAdmin;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })    @JsonIgnore
    private List<User> waitingListForJoin;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })    @JsonIgnore
    private List<PrivatePost> waitingListForPrivatePosts;

    public GroupOfUsers(boolean accepted, List<NormalUser> users, List<PrivatePost> privatePosts, NormalUser groupAdmin) {
        this.accepted = accepted;
        this.users = users;
        this.privatePosts = privatePosts;
        this.groupAdmin = groupAdmin;
        waitingListForJoin = new ArrayList<User>();
        waitingListForPrivatePosts = new ArrayList<PrivatePost>();
    }

    public GroupOfUsers() {
    }
}

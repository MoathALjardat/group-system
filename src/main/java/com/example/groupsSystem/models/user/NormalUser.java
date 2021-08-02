package com.example.groupsSystem.models.user;

import com.example.groupsSystem.models.group.GroupOfUsers;
import com.example.groupsSystem.models.post.Post;
import com.example.groupsSystem.models.post.PrivatePost;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode
public class NormalUser extends User {

    public NormalUser() {
        super();
    }

    public NormalUser(List<GroupOfUsers> groupsThisUserAdminInThere, List<GroupOfUsers> groupsThisUserJoinedInThere) {
        super();
        this.groupsThisUserAdminInThere = groupsThisUserAdminInThere;
        this.groupsThisUserJoinedInThere = groupsThisUserJoinedInThere;
    }

    public NormalUser(String userName, String password, List<Post> posts, List<GroupOfUsers> groupsThisUserAdminInThere, List<GroupOfUsers> groupsThisUserJoinedInThere) {
        super(userName, password, posts);
        this.groupsThisUserAdminInThere = groupsThisUserAdminInThere;
        this.groupsThisUserJoinedInThere = groupsThisUserJoinedInThere;
    }

    @OneToMany
    private List <GroupOfUsers> groupsThisUserAdminInThere ;

    @ManyToMany
    private List <GroupOfUsers> groupsThisUserJoinedInThere ;



}

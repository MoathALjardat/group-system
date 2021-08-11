package com.example.groupsSystem.models.user;

import com.example.groupsSystem.models.group.GroupOfUsers;
import com.example.groupsSystem.models.post.Post;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@EqualsAndHashCode
public class NormalUser extends User {

    public NormalUser() {
        super();
    }

    public NormalUser(List<GroupOfUsers> groupsThisUserAdminInThere, List<GroupOfUsers> groupsThisUserJoinedInThere) {
        this.groupsThisUserAdminInThere = groupsThisUserAdminInThere;
        this.groupsThisUserJoinedInThere = groupsThisUserJoinedInThere;
    }

    public NormalUser(String username, String password, List<Post> posts, List<GroupOfUsers> groupsThisUserAdminInThere, List<GroupOfUsers> groupsThisUserJoinedInThere) {
        this.username =username ;
        this.password =password ;
        this.setPosts(posts);
        this.groupsThisUserAdminInThere = groupsThisUserAdminInThere;
        this.groupsThisUserJoinedInThere = groupsThisUserJoinedInThere;
    }

    @OneToMany(
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List <GroupOfUsers> groupsThisUserAdminInThere ;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private List <GroupOfUsers> groupsThisUserJoinedInThere ;
}

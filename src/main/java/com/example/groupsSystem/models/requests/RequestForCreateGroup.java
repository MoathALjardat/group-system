package com.example.groupsSystem.models.requests;

import com.example.groupsSystem.models.group.GroupOfUsers;
import com.example.groupsSystem.models.user.NormalUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class RequestForCreateGroup {

    @Id
    @GeneratedValue
    int id ;

    @ManyToOne(
            cascade = CascadeType.ALL)
    @JoinColumn
    private NormalUser groupAdmin;

    @ManyToOne(
            cascade = CascadeType.ALL)
    @JoinColumn
    private GroupOfUsers groupOfUsers ;

}

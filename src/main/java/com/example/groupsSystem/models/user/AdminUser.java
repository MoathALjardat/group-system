package com.example.groupsSystem.models.user;


import lombok.EqualsAndHashCode;
import javax.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode
public class AdminUser extends User {

    public AdminUser() {

    }

}

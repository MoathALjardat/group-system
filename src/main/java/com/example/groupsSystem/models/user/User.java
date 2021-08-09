package com.example.groupsSystem.models.user;


import com.example.groupsSystem.securty.auth.AppUser;
import com.example.groupsSystem.models.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode
public class User extends AppUser {

    public String username;
    public String password;

    public User() {
        super();
    }

    public User(Set<? extends GrantedAuthority> grantedAuthorities, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, String username, String password, int id, List<Post> posts, String roles) {
        super(grantedAuthorities, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, username, password);
        this.id = id;
        this.posts = posts;
        this.roles = roles;
        this.username = username ;
        this.password = password ;
    }

    public User(int id, List<Post> posts, String roles) {
        this.id = id;
        this.posts = posts;
        this.roles = roles;
    }

    @Id
    @GeneratedValue
    protected int id;


    @OneToMany
    @JsonIgnore
    private List<Post> posts;

    private String roles;

}
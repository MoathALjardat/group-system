package com.example.groupsSystem.jwt;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;

    public UsernameAndPasswordAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UsernameAndPasswordAuthenticationRequest() {

    }
}

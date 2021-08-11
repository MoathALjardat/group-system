package com.example.groupsSystem.securty.authentication;

import java.util.Optional;

public interface ApplicationUserDao {
     Optional<ApplicationUser> selectAppUserByUsername (String username) ;
}

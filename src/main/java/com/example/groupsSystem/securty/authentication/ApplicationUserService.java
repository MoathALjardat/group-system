package com.example.groupsSystem.securty.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final ApplicationUserDao appUserDao ;

    @Autowired
    public ApplicationUserService(@Qualifier("fake") ApplicationUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserDao.selectAppUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("Username %s not found !!!" ,username)));
    }
}

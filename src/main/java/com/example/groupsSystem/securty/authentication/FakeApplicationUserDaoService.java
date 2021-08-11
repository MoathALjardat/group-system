package com.example.groupsSystem.securty.authentication;

import com.example.groupsSystem.models.user.NormalUser;
import com.example.groupsSystem.models.user.User;
import com.example.groupsSystem.repositories.user.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.groupsSystem.securty.ApplicationUserRole.ADMIN;
import static com.example.groupsSystem.securty.ApplicationUserRole.NORMAL;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectAppUserByUsername(String username) {
        return getAppUsers()
                .stream()
                .filter(appUser -> username.equals(appUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getAppUsers() {

        List<User> users = userRepository.findAll();

        List<ApplicationUser> appUsers = Lists.newArrayList(
                new ApplicationUser(
                        ADMIN.getGrantedAuthorities(),
                        true, true, true, true,
                        "layth",
                        passwordEncoder.encode("layth")
                ),
                new ApplicationUser(
                        NORMAL.getGrantedAuthorities(),
                        true, true, true, true,
                        "ramiz",
                        passwordEncoder.encode("ramiz")
                ),
                new ApplicationUser(
                        NORMAL.getGrantedAuthorities(),
                        true, true, true, true,
                        "hamza",
                        passwordEncoder.encode("layth")
                )
        );

        for (User user : users) {
            if (user instanceof NormalUser) {
                appUsers.add(new ApplicationUser(
                        NORMAL.getGrantedAuthorities(),
                        true, true, true, true,
                        user.getUsername(),
                        passwordEncoder.encode(user.getPassword()+"")

                ));
            } else {
                appUsers.add(
                        new ApplicationUser(
                                ADMIN.getGrantedAuthorities(),
                                true, true, true, true,
                                user.getUsername(),
                                passwordEncoder.encode(user.getPassword()+"")
                        ));
            }
        }
        return appUsers;
    }
}

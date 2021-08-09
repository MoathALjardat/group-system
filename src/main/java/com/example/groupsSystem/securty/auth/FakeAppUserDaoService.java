package com.example.groupsSystem.securty.auth;

import com.example.groupsSystem.models.user.NormalUser;
import com.example.groupsSystem.models.user.User;
import com.example.groupsSystem.repositories.user.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.groupsSystem.securty.AppUserRole.ADMIN;
import static com.example.groupsSystem.securty.AppUserRole.NORMAL;

@Repository("fake")
public class FakeAppUserDaoService implements AppUserDao {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Autowired
    public FakeAppUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUser> selectAppUserByUsername(String username) {
        return getAppUsers()
                .stream()
                .filter(appUser -> username.equals(appUser.getUsername()))
                .findFirst();
    }

    private List<AppUser> getAppUsers() {

        List<User> users = userRepository.findAll();

        List<AppUser> appUsers = Lists.newArrayList(
                new AppUser(
                        ADMIN.getGrantedAuthorities(),
                        true, true, true, true,
                        "layth",
                        passwordEncoder.encode("layth")
                ),
                new AppUser(
                        NORMAL.getGrantedAuthorities(),
                        true, true, true, true,
                        "ramiz",
                        passwordEncoder.encode("ramiz")
                ),
                new AppUser(
                        NORMAL.getGrantedAuthorities(),
                        true, true, true, true,
                        "hamza",
                        passwordEncoder.encode("layth")
                )
        );

        for (User user : users) {
            if (user instanceof NormalUser) {
                appUsers.add(new AppUser(
                        NORMAL.getGrantedAuthorities(),
                        true, true, true, true,
                        user.getUsername(),
                        passwordEncoder.encode(user.getPassword()+"")

                ));
            } else {
                appUsers.add(
                        new AppUser(
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

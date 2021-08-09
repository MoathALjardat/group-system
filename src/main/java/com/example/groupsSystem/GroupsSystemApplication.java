package com.example.groupsSystem;

import com.example.groupsSystem.models.user.NormalUser;
import com.example.groupsSystem.repositories.user.NormalUserRepository;
import com.example.groupsSystem.repositories.user.UserRepository;
import com.example.groupsSystem.services.user.NormalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class GroupsSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(GroupsSystemApplication.class, args);

	}

}

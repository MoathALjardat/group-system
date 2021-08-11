package com.example.groupsSystem.repositories.user;

import com.example.groupsSystem.models.user.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NormalUserRepository extends JpaRepository<NormalUser,Integer> {
}

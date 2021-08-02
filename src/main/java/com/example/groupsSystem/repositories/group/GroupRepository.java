package com.example.groupsSystem.repositories.group;

import com.example.groupsSystem.models.group.GroupOfUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupOfUsers,Integer> {
}

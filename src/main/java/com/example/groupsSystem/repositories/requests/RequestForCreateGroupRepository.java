package com.example.groupsSystem.repositories.requests;

import com.example.groupsSystem.models.requests.RequestForCreateGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestForCreateGroupRepository extends JpaRepository<RequestForCreateGroup, Integer> {
}

package com.example.groupsSystem.services.group;

import com.example.groupsSystem.models.group.GroupOfUsers;
import com.example.groupsSystem.repositories.group.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;


    public int getNumberOfGroups() {
        return (int) groupRepository.count();
    }

    public List<GroupOfUsers> getGroups() {
        return groupRepository.findAll();
    }

    public GroupOfUsers getGroupById(int id) {
        return groupRepository.findById(id).orElse(null);
    }

    public GroupOfUsers addGroup(GroupOfUsers group) {
        return groupRepository.save(group);
    }

    public GroupOfUsers deleteGroup(int id) {
        GroupOfUsers deletedGroup = groupRepository.findById(id).orElse(null);
        groupRepository.deleteById(id);
        return deletedGroup;
    }

    public GroupOfUsers updateGroup(GroupOfUsers group) {
        groupRepository.save(group);
        return group;
    }
}

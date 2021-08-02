package com.example.groupsSystem.services.user;

import com.example.groupsSystem.models.group.GroupOfUsers;
import com.example.groupsSystem.models.post.PublicPost;
import com.example.groupsSystem.models.requests.RequestForCreateGroup;
import com.example.groupsSystem.models.requests.RequestForPublicPost;
import com.example.groupsSystem.models.user.AdminUser;
import com.example.groupsSystem.repositories.group.GroupRepository;
import com.example.groupsSystem.repositories.post.PublicPostRepository;
import com.example.groupsSystem.repositories.requests.RequestForPublicPostRepository;
import com.example.groupsSystem.repositories.requests.RequestForCreateGroupRepository;
import com.example.groupsSystem.repositories.user.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    public int getNumberOfAdminUsers() {
        return (int) adminUserRepository.count();
    }

    public List<AdminUser> getAdminUsers() {
        return adminUserRepository.findAll();
    }

    public AdminUser getAdminUserById(int id) {
        return adminUserRepository.findById(id).orElse(null);
    }

    public AdminUser addAdminUser(AdminUser adminUser) {
        return adminUserRepository.save(adminUser);
    }

    public AdminUser deleteAdminUser(int id) {
        AdminUser deletedAdminUser = adminUserRepository.findById(id).orElse(null);
        adminUserRepository.deleteById(id);
        return deletedAdminUser;
    }

    public AdminUser updateAdminUser(AdminUser adminUser) {
        adminUserRepository.save(adminUser);
        return adminUser;
    }


    @Autowired
    RequestForCreateGroupRepository requestForCreateGroupRepo ;
    @Autowired
    GroupRepository groupRepository ;
    public void acceptGroup(RequestForCreateGroup requestToCreateGroup) {

        GroupOfUsers groupOfUsers = requestToCreateGroup.getGroupOfUsers();
        groupOfUsers.setAccepted(true);
        groupOfUsers.setGroupAdmin(requestToCreateGroup.getGroupAdmin());

        requestForCreateGroupRepo.deleteById(requestToCreateGroup.getId());

        groupRepository.save(groupOfUsers);
    }


    @Autowired
    RequestForPublicPostRepository reqestForPublicPostRepo ;
    @Autowired
    PublicPostRepository publicPostRepository ;
    public void acceptPublicPost(RequestForPublicPost requestsForPublicPostFromManger) {

        PublicPost publicPost = requestsForPublicPostFromManger.getPublicPost();
        publicPost.setAccepted(true);
        reqestForPublicPostRepo.deleteById(requestsForPublicPostFromManger.getId());
        publicPostRepository.save(publicPost);
    }
}

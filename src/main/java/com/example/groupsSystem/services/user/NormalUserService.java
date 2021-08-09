package com.example.groupsSystem.services.user;

import com.example.groupsSystem.models.comment.Comment;
import com.example.groupsSystem.models.group.GroupOfUsers;
import com.example.groupsSystem.models.post.Post;
import com.example.groupsSystem.models.post.PrivatePost;
import com.example.groupsSystem.models.post.PublicPost;
import com.example.groupsSystem.models.requests.RequestForCreateGroup;
import com.example.groupsSystem.models.requests.RequestForPublicPost;
import com.example.groupsSystem.models.user.NormalUser;
import com.example.groupsSystem.models.user.User;
import com.example.groupsSystem.repositories.requests.RequestForPublicPostRepository;
import com.example.groupsSystem.repositories.requests.RequestForCreateGroupRepository;
import com.example.groupsSystem.repositories.user.NormalUserRepository;
import com.example.groupsSystem.services.comment.CommentService;
import com.example.groupsSystem.services.group.GroupService;
import com.example.groupsSystem.services.post.PrivatePostService;
import com.example.groupsSystem.services.post.PublicPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NormalUserService {

    @Autowired
    private NormalUserRepository normalUserRepository;


    public int getNumberOfNormalUsers() {
        return (int) normalUserRepository.count();
    }

    public List<NormalUser> getNormalUsers() {
        return normalUserRepository.findAll();
    }

    public NormalUser getNormalUserById(int id) {
        return normalUserRepository.findById(id).orElse(null);
    }

    public NormalUser addNormalUser(NormalUser normalUser) {
        return normalUserRepository.save(normalUser);
    }

    public NormalUser deleteNormalUser(int id) {
        NormalUser deletedNormalUser = normalUserRepository.findById(id).orElse(null);
        normalUserRepository.deleteById(id);
        return deletedNormalUser;
    }

    public NormalUser updateNormalUser(NormalUser normalUser) {
        normalUserRepository.save(normalUser);
        return normalUser;
    }

    @Autowired
    RequestForCreateGroupRepository requestForCreateGroupRepo;

    public void requestForCreatingGroupFromManger(NormalUser groupAdmin) {

        GroupOfUsers groupOfUsers = new GroupOfUsers();
        groupOfUsers.setGroupAdmin(groupAdmin);

        RequestForCreateGroup requestToCreateGroup = new RequestForCreateGroup();

        requestToCreateGroup.setGroupOfUsers(groupOfUsers);
        requestToCreateGroup.setGroupAdmin(groupAdmin);

        requestForCreateGroupRepo.save(requestToCreateGroup);
    }

    @Autowired
    RequestForPublicPostRepository reqestForPublicPostRepo;

    public void requestForPublicPost(PublicPost publicPost, NormalUser writer) {

        publicPost.setWriter(writer);

        RequestForPublicPost requestsForPublicPostFromManger = new RequestForPublicPost();

        requestsForPublicPostFromManger.setPublicPost(publicPost);

        reqestForPublicPostRepo.save(requestsForPublicPostFromManger);

    }

    public void requestForPrivatePostInGroup(PrivatePost privatePost, NormalUser writer, GroupOfUsers groupOfUsers) {
        privatePost.setWriter(writer);
        privatePost.setGroup(groupOfUsers);
        groupOfUsers.getWaitingListForPrivatePosts().add(privatePost);
    }

    public void requestForJoinGroupFromGroupAdmin(GroupOfUsers groupOfUsers, NormalUser normalUser) {
        groupOfUsers.getWaitingListForJoin().add(normalUser);
    }

    public void commentInPost(Post post, Comment comment) {

        comment.setPost(post);
        CommentService commentService = new CommentService();
        commentService.addComment(comment);
        post.getCommentList().add(comment);

        if (post instanceof PrivatePost) {
            PrivatePostService privatePostService = new PrivatePostService();
            privatePostService.updatePrivatePost((PrivatePost) post);
        } else if (post instanceof PublicPost) {
            PublicPostService publicPostService = new PublicPostService();
            publicPostService.updatePublicPost((PublicPost) post);
        }
    }


    public void acceptPrivatePost(PrivatePost privatePost, NormalUser groupAdmin) {

        if (!privatePost.getGroup().getGroupAdmin().equals(groupAdmin))
            return;

        privatePost.setAccepted(true);
        privatePost.getGroup().getPrivatePosts().add(privatePost);

        privatePost.getGroup().getWaitingListForPrivatePosts().remove(privatePost);

        PrivatePostService privatePostService = new PrivatePostService();
        privatePostService.addPrivatePost(privatePost);

        GroupService groupService = new GroupService();
        groupService.updateGroup(privatePost.getGroup());
    }

    public void acceptToJoinGroup(GroupOfUsers groupOfUsers, NormalUser normalUser, NormalUser groupAdmin) {

        if (!groupOfUsers.getGroupAdmin().equals(groupAdmin))
            return;

        groupOfUsers.getWaitingListForJoin().remove(normalUser);
        groupOfUsers.getUsers().add(normalUser);

        GroupService groupService = new GroupService();
        groupService.updateGroup(groupOfUsers);
    }
}
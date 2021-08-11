package com.example.groupsSystem.services.user;

import com.example.groupsSystem.models.comment.Comment;
import com.example.groupsSystem.models.group.GroupOfUsers;
import com.example.groupsSystem.models.post.Post;
import com.example.groupsSystem.models.post.PrivatePost;
import com.example.groupsSystem.models.post.PublicPost;
import com.example.groupsSystem.models.requests.RequestForCreateGroup;
import com.example.groupsSystem.models.requests.RequestForPublicPost;
import com.example.groupsSystem.models.user.NormalUser;
import com.example.groupsSystem.repositories.group.GroupRepository;
import com.example.groupsSystem.repositories.post.PostRepository;
import com.example.groupsSystem.repositories.requests.RequestForPublicPostRepository;
import com.example.groupsSystem.repositories.requests.RequestForCreateGroupRepository;
import com.example.groupsSystem.repositories.user.NormalUserRepository;
import com.example.groupsSystem.services.comment.CommentService;
import com.example.groupsSystem.services.group.GroupService;
import com.example.groupsSystem.services.post.PrivatePostService;
import com.example.groupsSystem.services.post.PublicPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NormalUserService {

    @Autowired
    private NormalUserRepository normalUserRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    RequestForCreateGroupRepository requestForCreateGroupRepo;
    @Autowired
    RequestForPublicPostRepository requestForPublicPostRepository;
    @Autowired
    PostRepository postRepository;


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


    public void requestForCreatingGroupFromManger() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username.equals(""))
            return;

        NormalUser groupAdmin = findUserByUsername(username);

        GroupOfUsers groupOfUsers = new GroupOfUsers();
        groupOfUsers.setGroupAdmin(groupAdmin);

        RequestForCreateGroup requestToCreateGroup = new RequestForCreateGroup();

        requestToCreateGroup.setGroupOfUsers(groupOfUsers);
        requestToCreateGroup.setGroupAdmin(groupAdmin);

        requestForCreateGroupRepo.save(requestToCreateGroup);
    }


    public void requestForPublicPost(PublicPost publicPost) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username.equals(""))
            return;

        NormalUser writer = findUserByUsername(username);


        publicPost.setWriter(writer);

        RequestForPublicPost requestsForPublicPostFromManger = new RequestForPublicPost();

        requestsForPublicPostFromManger.setPublicPost(publicPost);

        requestForPublicPostRepository.save(requestsForPublicPostFromManger);
    }


    public void requestForPrivatePostInGroup(PrivatePost privatePost, int groupOfUsersId) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username.equals(""))
            return;

        NormalUser writer = findUserByUsername(username);

        GroupOfUsers groupOfUsers = groupRepository.findById(groupOfUsersId).orElse(null);

        if (groupOfUsers == null || !groupOfUsers.getUsers().contains(writer))
            return;

        privatePost.setWriter(writer);
        privatePost.setGroup(groupOfUsers);
        groupOfUsers.getWaitingListForPrivatePosts().add(privatePost);


        GroupService groupService = new GroupService();
        groupService.updateGroup(groupOfUsers);
    }

    public void acceptPrivatePost(int privatePostId, int groupId) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username.equals(""))
            return;

        NormalUser groupAdmin = findUserByUsername(username);


        GroupOfUsers groupOfUsers = groupRepository.findById(groupId).orElse(null);

        if (groupOfUsers == null)
            return;

        if (!groupOfUsers.getGroupAdmin().equals(groupAdmin))
            return;

        PrivatePost privatePost =
                groupOfUsers.getPrivatePosts().stream()
                        .filter(privatePost1 -> new Integer(privatePostId).equals(privatePost1.getId()))
                        .findAny()
                        .orElse(null);

        if (privatePost == null)
            return;


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


    public void requestForJoinGroupFromGroupAdmin(int groupOfUsersId) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username.equals(""))
            return;

        NormalUser normalUser = findUserByUsername(username);

        GroupOfUsers groupOfUsers = groupRepository.findById(groupOfUsersId).orElse(null);

        if (groupOfUsers == null)
            return;

        if (groupOfUsers.getUsers().contains(normalUser))
            return;


        groupOfUsers.getWaitingListForJoin().add(normalUser);

        GroupService groupService = new GroupService();
        groupService.updateGroup(groupOfUsers);
    }

    public void acceptToJoinGroup(int groupOfUsersId, int normalUserId) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username.equals(""))
            return;

        NormalUser groupAdmin = findUserByUsername(username);

        GroupOfUsers groupOfUsers = groupRepository.findById(groupOfUsersId).orElse(null);

        if (!groupOfUsers.getGroupAdmin().equals(groupAdmin))
            return;

        NormalUser normalUser = (NormalUser) groupOfUsers.getWaitingListForJoin().stream()
                .filter(user -> new Integer(normalUserId).equals(user.getId()))
                .findAny()
                .orElse(null);

        if (normalUser == null)
            return;

        groupOfUsers.getWaitingListForJoin().remove(normalUser);
        groupOfUsers.getUsers().add(normalUser);

        GroupService groupService = new GroupService();
        groupService.updateGroup(groupOfUsers);
    }

    public void commentInPost(int postId, Comment comment) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username.equals(""))
            return;

        NormalUser commentWritter = findUserByUsername(username);

        Post post = postRepository.findById(postId).orElse(null);

        if (post == null)
            return;

        if (post instanceof PrivatePost) {

            GroupOfUsers groupOfUsers = ((PrivatePost) post).getGroup();

            if (!groupOfUsers.getUsers().contains(commentWritter))
                return;

            comment.setPost(post);
            comment.setWritter(commentWritter);
            CommentService commentService = new CommentService();
            commentService.addComment(comment);
            post.getCommentList().add(comment);

            PrivatePostService privatePostService = new PrivatePostService();
            privatePostService.updatePrivatePost((PrivatePost) post);

        } else if (post instanceof PublicPost) {
            comment.setPost(post);
            comment.setWritter(commentWritter);

            CommentService commentService = new CommentService();
            commentService.addComment(comment);

            post.getCommentList().add(comment);

            PublicPostService publicPostService = new PublicPostService();
            publicPostService.updatePublicPost((PublicPost) post);
        }
    }

    private NormalUser findUserByUsername(String username)
    {
        NormalUser normalUser =
                normalUserRepository.findAll().stream()
                        .filter(normalUser1 -> username.equals(normalUser1))
                        .findAny()
                        .orElse(null);

        return normalUser;

    }
}
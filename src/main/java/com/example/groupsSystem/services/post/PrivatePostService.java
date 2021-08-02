package com.example.groupsSystem.services.post;

import com.example.groupsSystem.models.post.PrivatePost;
import com.example.groupsSystem.repositories.post.PrivatePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PrivatePostService {
    @Autowired
    private PrivatePostRepository PrivatePostRepository;


    public int getNumberOfPrivatePosts() {
        return (int) PrivatePostRepository.count();
    }

    public List<PrivatePost> getPrivatePosts() {
        return PrivatePostRepository.findAll();


    }

    public PrivatePost getPrivatePostById(int id) {
        return PrivatePostRepository.findById(id).orElse(null);
    }

    public PrivatePost addPrivatePost(PrivatePost privatePost) {
        return PrivatePostRepository.save(privatePost);
    }

    public PrivatePost deletePrivatePost(int id) {
        PrivatePost deletedPrivatePost = PrivatePostRepository.findById(id).orElse(null);
        PrivatePostRepository.deleteById(id);
        return deletedPrivatePost;
    }

    public PrivatePost updatePrivatePost(PrivatePost privatePost) {
        PrivatePostRepository.save(privatePost);
        return privatePost;
    }
}

package com.example.groupsSystem.services.post;

import com.example.groupsSystem.models.post.PublicPost;
import com.example.groupsSystem.repositories.post.PublicPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicPostService {

    @Autowired
    private PublicPostRepository publicPostRepository;


    public int getNumberOfPublicPosts() {
        return (int) publicPostRepository.count();
    }

    public List<PublicPost> getPublicPosts() {
        return publicPostRepository.findAll();
    }

    public PublicPost getPublicPostById(int id) {
        return publicPostRepository.findById(id).orElse(null);
    }

    public PublicPost addPublicPost(PublicPost publicPost) {
        return publicPostRepository.save(publicPost);
    }

    public PublicPost deletePublicPost(int id) {
        PublicPost deletedPublicPost = publicPostRepository.findById(id).orElse(null);
        publicPostRepository.deleteById(id);
        return deletedPublicPost;
    }

    public PublicPost updatePublicPost(PublicPost publicPost) {
        publicPostRepository.save(publicPost);
        return publicPost;
    }

}
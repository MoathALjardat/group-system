package com.example.groupsSystem.services.post;

import com.example.groupsSystem.models.post.Post;
import com.example.groupsSystem.repositories.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;


    public int getNumberOfPosts() {
        return (int) postRepository.count();
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post deletePost(int id) {
        Post deletedPost = postRepository.findById(id).orElse(null);
        postRepository.deleteById(id);
        return deletedPost;
    }

    public Post updatePost(Post post) {
        postRepository.save(post);
        return post;
    }
}

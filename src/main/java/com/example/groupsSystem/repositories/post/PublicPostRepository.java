package com.example.groupsSystem.repositories.post;


import com.example.groupsSystem.models.post.PublicPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicPostRepository extends JpaRepository<PublicPost,Integer> {
}

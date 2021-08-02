package com.example.groupsSystem.repositories.post;

import com.example.groupsSystem.models.post.PrivatePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivatePostRepository extends JpaRepository<PrivatePost,Integer> {
}

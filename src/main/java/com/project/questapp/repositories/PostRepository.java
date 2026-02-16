package com.project.questapp.repositories;

import com.project.questapp.entities.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"user", "likes"})
    List<Post> findAll();

    @EntityGraph(attributePaths = {"user", "likes"})
    List<Post> findByUserId(Long userId);

    @EntityGraph(attributePaths = "user")
    Post findPostById(Long id);
}

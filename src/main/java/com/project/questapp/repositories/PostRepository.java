package com.project.questapp.repositories;

import com.project.questapp.entities.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"user", "likes"})
    List<Post> findAll();

    @EntityGraph(attributePaths = {"user", "likes"})
    List<Post> findByUserId(Long userId);

    @EntityGraph(attributePaths = "user")
    Post findPostById(Long id);

    @Query(value = "select id from posts where user_id = :userId order by create_at desc limit 5", nativeQuery = true)
    List<Long> findTopByUserId(@Param("userId") Long userId);
}

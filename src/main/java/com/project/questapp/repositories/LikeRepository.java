package com.project.questapp.repositories;

import com.project.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByPostId(Long postId);

    List<Like> findByUserId(Long userId);

    List<Like> findByUserIdAndPostId(Long userId, Long postId);
}

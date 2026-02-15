package com.project.questapp.repositories;

import com.project.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> getLikeByUser_IdAndPost_Id(Long userId, Long postId);

    List<Like> getLikeByPost_Id(Long postId);

    List<Like> getLikesByUserId(Long userId);
}

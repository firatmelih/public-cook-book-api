package com.project.questapp.repositories;

import com.project.questapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getCommentsByPost_IdAndUser_Id(Long postId, Long userId);

    List<Comment> getCommentsByPost_Id(Long postId);

    List<Comment> getCommentsByUser_Id(Long userId);
}

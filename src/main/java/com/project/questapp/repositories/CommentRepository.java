package com.project.questapp.repositories;

import com.project.questapp.dtos.ActivityDTO;
import com.project.questapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUserIdAndPostId(Long userId, Long postId);

    List<Comment> findByPostId(Long postId);

    List<Comment> findByUserId(Long userId);

    @Query("""
                select new com.project.questapp.dtos.ActivityDTO(
                    'comment',
                    c.id,
                    c.user.id,
                    c.post.id,
                    c.user.username,
                    concat(c.user.avatar, ''),
                    c.text,
                    c.createDate
                )
                from Comment c
                where c.post.id in :postIds
            """)
    List<ActivityDTO> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);
}

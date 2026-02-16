package com.project.questapp.services;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repositories.CommentRepository;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final PostService postService;
    private final UserService userService;
    CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository, PostService postService, UserService userService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
    }

    // CREATE
    public Comment createComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getUserById(commentCreateRequest.getUserId());
        Post post = postService.getPostEntityById(commentCreateRequest.getPostId());
        if (user == null || post == null) {
            return null;
        }
        Comment commentToSave = new Comment();
        commentToSave.setId(commentCreateRequest.getId());
        commentToSave.setText(commentCreateRequest.getText());
        commentToSave.setUser(user);
        commentToSave.setPost(post);
        return commentRepository.save(commentToSave);
    }
    // CREATE END

    // READ
    public List<Comment> getCommentsWithParams(Optional<Long> userId, Optional<Long> postId) {
        if (postId.isPresent() && userId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        }
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }
    // READ END

    // UPDATE
    public Comment updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(commentToUpdate);
        }
        return null;
    }
    // UPDATE END

    // DELETE
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
    // DELETE END


}

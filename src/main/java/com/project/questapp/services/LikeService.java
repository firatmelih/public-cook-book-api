package com.project.questapp.services;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repositories.LikeRepository;
import com.project.questapp.requests.LikeCreateRequest;
import com.project.questapp.responses.LikeResponse;
import com.project.questapp.security.JwtUserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private final PostService postService;
    private final UserService userService;
    LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository, PostService postService, UserService userService) {
        this.likeRepository = likeRepository;
        this.postService = postService;
        this.userService = userService;
    }

    // CREATE
    public Like createLike(JwtUserDetails userDetails, LikeCreateRequest likeCreateRequest) {
        User user = userService.getUserById(userDetails.getId());
        Post post = postService.getPostEntityById(likeCreateRequest.getPostId());
        if (user == null || post == null) {
            return null;
        }
        Like likeToSave = new Like();
        likeToSave.setUser(user);
        likeToSave.setPost(post);
        likeToSave.setCreateDate(LocalDateTime.now());
        return likeRepository.save(likeToSave);
    }
    // CREATE END

    // READ
    public List<LikeResponse> getLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> like;
        if (postId.isPresent() && userId.isPresent()) {
            like = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (postId.isPresent()) {
            like = likeRepository.findByPostId(postId.get());
        } else if (userId.isPresent()) {
            like = likeRepository.findByUserId(userId.get());
        } else {
            like = likeRepository.findAll();
        }
        return like.stream().map(LikeResponse::new).toList();
    }

    public LikeResponse getLikeById(Long likeId) {
        Like like = likeRepository.findById(likeId).get();
        return new LikeResponse(like);
    }
    // READ END

    // UPDATE
    // UPDATE END

    // DELETE
    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
    // DELETE END


}

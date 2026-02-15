package com.project.questapp.services;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repositories.LikeRepository;
import com.project.questapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

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
    public Like createLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getUserById(likeCreateRequest.getUserId());
        Post post = postService.getOnePostById(likeCreateRequest.getPostId());
        if (user == null || post == null) {
            return null;
        }
        Like likeToSave = new Like();
        likeToSave.setId(likeCreateRequest.getId());
        likeToSave.setUser(user);
        likeToSave.setPost(post);
        return likeRepository.save(likeToSave);
    }
    // CREATE END

    // READ
    public List<Like> getLikes(Optional<Long> postId, Optional<Long> userId) {
        if (postId.isPresent() && userId.isPresent()) {
            return likeRepository.getLikeByUser_IdAndPost_Id(userId.get(), postId.get());
        } else if (postId.isPresent()) {
            return likeRepository.getLikeByPost_Id(postId.get());
        } else if (userId.isPresent()) {
            return likeRepository.getLikesByUserId(userId.get());
        }
        return likeRepository.findAll();
    }

    public Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }
    // READ END

    // UPDATE
    // UPDATE END

    // DELETE
    public void deleteLike(Long likeId) {
        postService.deleteOnePostById(likeId);
    }
    // DELETE END


}

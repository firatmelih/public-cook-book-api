package com.project.questapp.controllers;

import com.project.questapp.entities.Like;
import com.project.questapp.requests.LikeCreateRequest;
import com.project.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {
        return likeService.getLikes(postId, userId);
    }

    @GetMapping("/{likeId}")
    public Like getCommentByCommentId(@PathVariable Long likeId) {
        return likeService.getLikeById(likeId);
    }

    @PostMapping
    public Like createComment(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeService.createLike(likeCreateRequest);
    }

//    @PutMapping("/{commentId}")
//    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentCreateRequest commentCreateRequest) {
//        return likeService.updateComment(commentId, commentCreateRequest);
//    }

    @DeleteMapping("/{likeId}")
    public void deleteComment(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
    }


}

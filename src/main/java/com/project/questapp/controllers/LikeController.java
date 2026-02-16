package com.project.questapp.controllers;

import com.project.questapp.entities.Like;
import com.project.questapp.requests.LikeCreateRequest;
import com.project.questapp.responses.LikeResponse;
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
    public List<LikeResponse> getLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return likeService.getLikes(userId, postId);
    }

    @GetMapping("/{likeId}")
    public Like getLikeByLikeId(@PathVariable Long likeId) {
        return likeService.getLikeById(likeId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeService.createLike(likeCreateRequest);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
    }

}

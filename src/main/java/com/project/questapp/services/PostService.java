package com.project.questapp.services;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repositories.PostRepository;
import com.project.questapp.requests.PostCreateRequest;
import com.project.questapp.requests.PostUpdateRequest;
import com.project.questapp.responses.PostResponse;
import com.project.questapp.security.JwtTokenProvider;
import com.project.questapp.security.JwtUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    // CREATE
    public PostResponse createOnePost(JwtUserDetails userDetails, PostCreateRequest request) {
        User user = userService.getUserById(userDetails.getId());

        Post toSave = new Post();
        toSave.setUser(user);
        toSave.setTitle(request.getTitle());
        toSave.setText(request.getText());
        toSave.setCreateDate(LocalDateTime.now());

        return new PostResponse(postRepository.save(toSave));
    }
    // CREATE END

    // READ
    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> posts;
        if (userId.isPresent()) {
            posts = postRepository.findByUserId(userId.get());
        } else {
            posts = postRepository.findAll();
        }
        return posts.stream().map(PostResponse::new).toList();
    }

    public PostResponse getOnePostById(Long postId) {
        Post p = postRepository.findPostById(postId);
        if (p == null) {
            return null;
        }
        return new PostResponse(p);
    }

    public Post getPostEntityById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
    // READ END

    // UPDATE
    public Post updateOnePostById(Long postId, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById((postId));
        if (post.isPresent()) {
            Post postToUpdate = post.get();
            postToUpdate.setTitle(postUpdateRequest.getTitle());
            postToUpdate.setText(postUpdateRequest.getText());
            return postRepository.save(postToUpdate);
        }
        return null;
    }
    // UPDATE END

    // DELETE
    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
    // DELETE END


}

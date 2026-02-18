package com.project.questapp.responses;

import com.project.questapp.entities.Post;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostResponse {
    Long id;
    Long userId;
    String username;
    String title;
    String text;
    List<LikeResponse> postLikes;

    public PostResponse(Post entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.username = entity.getUser().getUsername();
        this.title = entity.getTitle();
        this.text = entity.getText();

        if (entity.getLikes() != null) {
            this.postLikes = entity.getLikes().stream()
                    .map(LikeResponse::new)
                    .collect(Collectors.toList());
        } else {
            this.postLikes = List.of(); // Returns an empty immutable list
        }
    }
}
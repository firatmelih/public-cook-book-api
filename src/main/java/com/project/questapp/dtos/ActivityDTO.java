package com.project.questapp.dtos;

import java.time.LocalDateTime;

public record ActivityDTO(
        String type,
        Long id,
        Long userId,
        Long postId,
        String username,
        String avatar,
        String content,
        LocalDateTime createDate
) {
}
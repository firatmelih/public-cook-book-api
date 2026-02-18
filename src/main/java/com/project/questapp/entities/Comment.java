package com.project.questapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.EAGER would bring user's data from request to post
    @JoinColumn(name = "user_id", nullable = false) // without user you can't create post
    @OnDelete(action = OnDeleteAction.CASCADE) // if user deleted, delete their posts
    @JsonIgnore // serialization ignore bcs we won't fetch
    User user;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.EAGER would bring user's data from request to post
    @JoinColumn(name = "post_id", nullable = false) // without user you can't create post
    @OnDelete(action = OnDeleteAction.CASCADE) // if user deleted, delete their posts
    @JsonIgnore // serialization ignore bcs we won't fetch
    Post post;

    @Lob
    @Column(columnDefinition = "TEXT")
    String text;

    @Column(name = "created_at", nullable = false)
    LocalDateTime createDate;
}

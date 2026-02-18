package com.project.questapp.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY) // FetchType.EAGER would bring user's data from request to post
    @JoinColumn(name = "user_id", nullable = false) // without user you can't create post
    @OnDelete(action = OnDeleteAction.CASCADE) // if user deleted, delete their posts
    User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Like> likes;

    String title;
    @Lob
    @Column(columnDefinition = "TEXT")
    String text;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createDate;
}

package com.githiomi.sentrivault.data.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: <a href="https://github.com/danielgithiomi">danielgithiomi</a>
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

@Data
@Entity
@Table(name = "comments", schema = "sentri_vault_schema")
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies = new ArrayList<>();

    private Integer likeCounter = 0;
    private Integer dislikeCounter = 0;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime lastUpdatedAt;

}

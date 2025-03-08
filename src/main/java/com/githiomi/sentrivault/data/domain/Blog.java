package com.githiomi.sentrivault.data.domain;

import com.githiomi.sentrivault.data.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: <a href="https://github.com/githiomi">danielgithiomi</a>
 * Version: 1.0.0
 * Created: 09, Jan 2025
 **/

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blogs", schema = "sentri_vault_schema")
public class Blog {

    @Id
    @Column(name = "blog_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String slug;

    @Lob
    private String content;

    private String bannerImageUrl;
    private Integer likeCounter = 0;
    private Integer dislikeCounter = 0;
    private Integer viewCounter = 0;

    @ElementCollection
    @Column(name = "blog_tags")
    private List<String> tags;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User authorId;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime lastUpdatedAt;

}

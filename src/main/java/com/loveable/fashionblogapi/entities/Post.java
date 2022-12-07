package com.loveable.fashionblogapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Post")
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String post;
    @Column(nullable = false)
    private  String category;
    @Column(nullable = false)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(
                    name = "blog_post_fk"
            )
    )
    private User user;
    @JsonIgnore
    @OneToMany(
            mappedBy = "post",
            orphanRemoval = true,
            cascade = ALL,
            fetch = LAZY
    )
    private List<Comment> comments;
    @JsonIgnore
    @OneToMany(
            mappedBy = "post",
            orphanRemoval = true,
            cascade = ALL
    )
    private List<PostLike> postLikes;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }


    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", post='" + post + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

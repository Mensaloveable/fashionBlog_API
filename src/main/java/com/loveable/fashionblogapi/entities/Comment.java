package com.loveable.fashionblogapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Comment")
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;
    @Column(nullable = false)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(
                    name = "user_comment_fk"
            )
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "post_id",
            nullable = false,
            referencedColumnName = "post_id",
            foreignKey = @ForeignKey(
                    name = "post_comment_fk"
            )
    )
    private Post post;

    @OneToMany(
            mappedBy = "comment",
            cascade = ALL,
            orphanRemoval = true
    )
    private List<CommentLike> commentLikes;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

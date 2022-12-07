package com.loveable.fashionblogapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "PostLike")
@Table(name = "post_like")
public class PostLike {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_like_id")
    private Long likeId;
//    private Boolean like;
    private Date createdAt;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(
                    name = "user_like_fk"
            )
    )
    private User user;
    @ManyToOne
    @JoinColumn(
            name = "post_id",
            nullable = false,
            referencedColumnName = "post_id",
            foreignKey = @ForeignKey(
                    name = "post_like_fk"
            )
    )
    private Post post;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }
//    @ManyToOne
//    @JoinColumn(
//            name = "comment_id",
//            nullable = false,
//            referencedColumnName = "comment_id",
//            foreignKey = @ForeignKey(
//                    name = "like_comment_fk"
//            )
//    )
//    private Comment comment;

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
//                ", like=" + like +
                ", createdAt=" + createdAt +
                '}';
    }
}

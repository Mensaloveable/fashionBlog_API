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
@Entity(name = "CommentLike")
@Table(name = "comment_like")
public class CommentLike {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comment_like_id")
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
//    @ManyToOne
//    @JoinColumn(
//            name = "post_id",
//            nullable = false,
//            referencedColumnName = "post_id",
//            foreignKey = @ForeignKey(
//                    name = "post_like_fk"
//            )
//    )
//    private Post post;
    @ManyToOne
    @JoinColumn(
            name = "comment_id",
            nullable = false,
            referencedColumnName = "comment_id",
            foreignKey = @ForeignKey(
                    name = "comment_like_fk"
            )
    )
    private Comment comment;

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
//                ", like=" + like +
                ", createdAt=" + createdAt +
                '}';
    }
}

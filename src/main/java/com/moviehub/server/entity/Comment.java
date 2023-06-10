package com.moviehub.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "rating")
@Getter
@Setter
@IdClass(CommentId.class)
public class Comment {

    @Id
    @Column(name = "tmdb_id")
    private Long tmdbId;

    @Id
    @Column(name = "mail_or_id")
    private String mailOrId;

    @Column(name = "comment_")
    private String comment;

    @Column(name = "timestamp_")
    private Timestamp timestamp;


}

class CommentId implements Serializable {
    private Long tmdbId;
    private String mailOrId;
}
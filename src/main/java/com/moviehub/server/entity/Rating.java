package com.moviehub.server.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

import java.io.Serializable;

@Entity
@Table(name = "rating")
@Getter
@Setter
@IdClass(RatingId.class)
public class Rating {

    @Id
    @Column(name = "tmdb_id")
    private Long tmdbId;

    @Id
    @Column(name = "id_or_mail")
    private String idOrMail;

    @Column(name = "rating")
    private Float rating;


}

class RatingId implements Serializable {
    private Long tmdbId;
    private String idOrMail;
}

package com.moviehub.server.entity;

/**
 * @Project ：MovieHub-server
 * @File ：Movie.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/25 10:21
 **/
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "tmdb_id", nullable = false)
    private Long tmdbId;

    @Column(name = "adult", nullable = false)
    private boolean adult;

    @Column(name = "homepage")
    private String homepage;

    @Column(name = "original_language", nullable = false)
    private String originalLanguage;

    @Column(name = "original_title", nullable = false)
    private String originalTitle;

    @Lob
    @Column(name = "overview", nullable = false)
    private String overview;

    @Column(name = "popularity", nullable = false)
    private Double popularity;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "status")
    private String status;

    @Lob
    @Column(name = "tagline")
    private String tagline;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "revenue")
    private Long revenue;

    @Column(name = "budget")
    private Long budget;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "vote_average", nullable = false)
    private Float voteAverage;

    @Column(name = "vote_count", nullable = false)
    private Integer voteCount;



}

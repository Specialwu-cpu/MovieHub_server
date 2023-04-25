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

import java.time.LocalDate;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class Movie {

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
    private double popularity;

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
    private long revenue;

    @Column(name = "budget")
    private long budget;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "runtime")
    private int runtime;

    @Column(name = "vote_average", nullable = false)
    private float voteAverage;

    @Column(name = "vote_count", nullable = false)
    private int voteCount;

}

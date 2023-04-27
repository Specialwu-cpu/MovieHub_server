package com.moviehub.server.dto;

import com.moviehub.server.dto.PK.GenreAndMoviePK;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;

/**
 * @Project ：server
 * @File ：GenreAndMovieDTO.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/27 13:34
 **/
@Entity
@Table(name = "genre_and_movie")
@Getter
@IdClass(com.moviehub.server.dto.PK.GenreAndMoviePK.class)
public class GenreAndMovieDTO implements Serializable {
    @Id
    @Column(name = "genre_id")
    private int genreId;

    @Id
    @Column(name = "tmdb_id")
    private Long tmdbId;

    // constructors, getters and setters
}

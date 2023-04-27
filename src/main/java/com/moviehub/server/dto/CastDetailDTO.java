package com.moviehub.server.dto;

import com.moviehub.server.entity.Cast;
import com.moviehub.server.entity.Movie;

import java.util.List;

/**
 * @Project ：server
 * @File ：CastDetailDTO.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/27 18:43
 **/
public class CastDetailDTO {
    private Cast cast;
    private List<Movie> movies;

    public CastDetailDTO(Cast cast, List<Movie> movies) {
        this.cast = cast;
        this.movies = movies;
    }

    public Cast getCast() {
        return cast;
    }

    public void setCast(Cast cast) {
        this.cast = cast;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}


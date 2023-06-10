package com.moviehub.server.dto;

import com.moviehub.server.entity.Cast;
import com.moviehub.server.entity.Crew;
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
    private List<Movie> moviesByCast;

    private Crew crew;

    public List<Movie> getMoviesByCast() {
        return moviesByCast;
    }

    public void setMoviesByCast(List<Movie> moviesByCast) {
        this.moviesByCast = moviesByCast;
    }

    public List<Movie> getMoviesByCrew() {
        return moviesByCrew;
    }

    public void setMoviesByCrew(List<Movie> moviesByCrew) {
        this.moviesByCrew = moviesByCrew;
    }

    private List<Movie> moviesByCrew;

    public CastDetailDTO(Cast cast, List<Movie> moviesByCast, Crew crew, List<Movie> moviesByCrew) {
        this.cast = cast;
        this.moviesByCast = moviesByCast;
        this.crew = crew;
        this.moviesByCrew = moviesByCrew;
    }

    public Cast getCast() {
        return cast;
    }

    public void setCast(Cast cast) {
        this.cast = cast;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }
}


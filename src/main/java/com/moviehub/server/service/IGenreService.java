package com.moviehub.server.service;

import com.moviehub.server.entity.Movie;

import java.util.List;

/**
 * @Project ：server
 * @File ：IGenreService.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/27 13:46
 **/
public interface IGenreService {
    List<Movie> getMoviesByGenreName(String genreName);
}

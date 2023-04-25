package com.moviehub.server.service.impl;

import com.moviehub.server.entity.Movie;
import com.moviehub.server.repository.MovieRepository;
import com.moviehub.server.service.IMovieService;
import jakarta.annotation.Resource;

import java.util.List;

/**
 * @Project ：MovieHub-server
 * @File ：MovieServiceImpl.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/25 11:34
 **/
public class MovieServiceImpl implements IMovieService {

    @Resource
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }
}

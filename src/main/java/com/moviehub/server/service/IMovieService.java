package com.moviehub.server.service;

import com.moviehub.server.entity.Movie;
import com.moviehub.server.util.BaseResponse;

import java.util.List;
import java.util.Map;

/**
 * @Project ：MovieHub-server
 * @File ：IMovieService.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/25 11:33
 **/
public interface IMovieService {
    List<Movie> getAllMovies();

    BaseResponse getMovieForVisitor();
}

package com.moviehub.server.service;

import ai.onnxruntime.OrtException;
import com.moviehub.server.entity.Movie;
import com.moviehub.server.util.BaseResponse;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Project ：MovieHub-server
 * @File ：IMovieService.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/25 11:33
 **/
public interface IMovieService {
    List<Movie> getAllMovies();
    BaseResponse getMovieForYou(int page, String email) throws CsvValidationException, IOException, OrtException;

    BaseResponse getMovieForVisitor(int page);

    BaseResponse searchMoviews(String query);

//    BaseResponse searchMovieSuggest(String query);
}

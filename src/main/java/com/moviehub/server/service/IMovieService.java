package com.moviehub.server.service;

import com.moviehub.server.entity.Movie;
import com.moviehub.server.util.BaseResponse;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDate;
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
    BaseResponse getMovieForYou(int page, String email) throws CsvValidationException, IOException;

    BaseResponse getMovieForVisitor(int page);

    BaseResponse searchMoviews(String query);

    BaseResponse addMovie(boolean adult, String homepage, String originalLanguage, String originalTitle, String overview, Double popularity, String posterPath, String status, String tagline, String title, Long revenue, Long budget, LocalDate releaseDate, Integer runtime, Float voteAverage, Integer voteCount);

    BaseResponse updateMovie(Long tmdbId, boolean adult, String homepage, String originalLanguage, String originalTitle, String overview, double popularity, String posterPath, String status, String tagline, String title, long revenue, long budget, LocalDate releaseDate, int runtime, float voteAverage, int voteCount);

    BaseResponse deleteMovieByTmdbId(Long tmdbId);


//    BaseResponse searchMovieSuggest(String query);
}

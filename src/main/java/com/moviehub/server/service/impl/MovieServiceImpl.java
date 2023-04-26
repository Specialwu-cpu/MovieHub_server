package com.moviehub.server.service.impl;

import com.moviehub.server.DTO.MovieShowed;
import com.moviehub.server.entity.Movie;
import com.moviehub.server.repository.MovieRepository;
import com.moviehub.server.service.IMovieService;
import com.moviehub.server.util.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project ：MovieHub-server
 * @File ：MovieServiceImpl.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/25 11:34
 **/
@Service
public class MovieServiceImpl implements IMovieService {

    @Resource
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    @Override
    public BaseResponse getMovieForVisitor() {
        //都给客户端
        List<Movie> popularMovie = movieRepository.findMovieMostPopular();
        List<Movie> earnMovie = movieRepository.findMovieMostEarn();
        List<Movie> todayMovie = movieRepository.findTodayMovie();


        HashMap<String, List<Movie>> data = new HashMap<>();
        data.put("Popular", popularMovie);
        data.put("HighestBox", earnMovie);
        data.put("Today", todayMovie);


        return BaseResponse.success(data);

    }

}

package com.moviehub.server.service.impl;

import com.moviehub.server.DTO.MovieShowed;
import com.moviehub.server.entity.Movie;
import com.moviehub.server.repository.MovieRepository;
import com.moviehub.server.service.IMovieService;
import com.moviehub.server.util.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
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

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    //还得想想怎么弄分页瀑布
    @Override
    public BaseResponse getMovieForVisitor() {


        final String key = "movie:three";
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        HashMap<String, List<Movie>> data = (HashMap<String, List<Movie>>) valueOperations.get(key);

        if (data == null){
            List<Movie> popularMovie = movieRepository.findMovieMostPopular();
            List<Movie> earnMovie = movieRepository.findMovieMostEarn();
            List<Movie> todayMovie = movieRepository.findTodayMovie();

            data = new HashMap<>();
            System.out.println("fuck");
            data.put("Popular", popularMovie);
            data.put("HighestBox", earnMovie);
            data.put("Today", todayMovie);
            valueOperations.set(key, data, 1, TimeUnit.HOURS);
        }
        //都给客户端



        return BaseResponse.success(data);

    }

}

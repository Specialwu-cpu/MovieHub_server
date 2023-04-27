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
    public BaseResponse getMovieForVisitor(int page) {
        final String key = "movie:choice_guest_start" + page;
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        if (page == 0){
            HashMap<String, List<Movie>> data = (HashMap<String, List<Movie>>) valueOperations.get(key);

            if (data == null){
                List<Movie> popularMovie = movieRepository.findMovieMostPopular();
                List<Movie> earnMovie = movieRepository.findMovieMostEarn();
                List<Movie> todayMovie = movieRepository.findTodayMovie();
                List<Movie> recommendMovie = movieRepository.findForRecommendGuest(20, 20 * page);

                data = new HashMap<>();
                data.put("Popular", popularMovie);
                data.put("HighestBox", earnMovie);
                data.put("Today", todayMovie);
                data.put("Recommend", recommendMovie);
                valueOperations.set(key, data, 1, TimeUnit.HOURS);
            }
            //都给客户端



            return BaseResponse.success(data);
        }
        else {
            List<Movie> data = (List<Movie>) valueOperations.get(key);
            if (data == null){
                List<Movie> newData = movieRepository.findForRecommendGuest(20, 20 * page);
                valueOperations.set(key, newData, 60 - page, TimeUnit.MINUTES);

                return BaseResponse.success(newData);
            }
            return BaseResponse.success(data);
        }

    }

}

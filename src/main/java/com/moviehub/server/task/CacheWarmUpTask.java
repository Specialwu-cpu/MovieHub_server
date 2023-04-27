//package com.moviehub.server.task;
//
//import com.moviehub.server.dto.GenreAndMovieDTO;
//import com.moviehub.server.entity.Genre;
//import com.moviehub.server.entity.Movie;
//import com.moviehub.server.repository.GenreAndMovieRepository;
//import com.moviehub.server.repository.GenreRepository;
//import com.moviehub.server.repository.MovieRepository;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Project ：server
// * @File ：CacheWarmUpTask.java
// * @IDE ：IntelliJ IDEA
// * @Author ：wsh ruan
// * @Date ：2023/4/27 14:12
// **/
//@Component
//public class CacheWarmUpTask {
//
//    private final GenreRepository genreRepository;
//    private final GenreAndMovieRepository genreAndMovieRepository;
//    private final MovieRepository movieRepository;
//    private final RedisTemplate<String, Object> redisTemplate;
//
//    public CacheWarmUpTask(GenreRepository genreRepository,
//                           GenreAndMovieRepository genreAndMovieRepository,
//                           MovieRepository movieRepository,
//                           RedisTemplate<String, Object> redisTemplate) {
//        this.genreRepository = genreRepository;
//        this.genreAndMovieRepository = genreAndMovieRepository;
//        this.movieRepository = movieRepository;
//        this.redisTemplate = redisTemplate;
//    }
//
//    @Scheduled(fixedRate = 3600000) // 每小时执行一次
//    public void warmUpCache() {
//        // 获取所有的电影种类
//        List<Genre> genres = genreRepository.findAll();
//        for (Genre genre : genres) {
//            String key = "genre:" + genre.getName() + ":movies";
//            List<Movie> moviesByGenres = (List<Movie>) redisTemplate.opsForValue().get(key);
//            if (moviesByGenres == null) {
//                List<GenreAndMovieDTO> genreAndMovieDTOs = genreAndMovieRepository.findGenreAndMovieDTOByGenreId(genre.getId());
//                List<Long> tmdbIds = new ArrayList<>();
//                for (GenreAndMovieDTO dto : genreAndMovieDTOs) {
//                    tmdbIds.add(dto.getTmdbId());
//                }
//                moviesByGenres = movieRepository.findAllByTmdbIds(tmdbIds);
//                redisTemplate.opsForValue().set(key, moviesByGenres, 1, TimeUnit.HOURS);
//            }
//        }
//    }
//}
//

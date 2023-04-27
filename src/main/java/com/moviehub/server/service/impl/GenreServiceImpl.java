package com.moviehub.server.service.impl;

import com.moviehub.server.dto.GenreAndMovieDTO;
import com.moviehub.server.entity.Genre;
import com.moviehub.server.entity.Movie;
import com.moviehub.server.repository.GenreAndMovieRepository;
import com.moviehub.server.repository.GenreRepository;
import com.moviehub.server.repository.MovieRepository;
import com.moviehub.server.service.IGenreService;
import com.moviehub.server.util.BaseResponse;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Project ：server
 * @File ：GenreServiceImpl.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/27 13:46
 **/
@Service
public class GenreServiceImpl implements IGenreService {
    @Resource
    private MovieRepository movieRepository;

    @Resource
    private GenreAndMovieRepository genreAndMovieRepository;

    @Resource
    private GenreRepository genreRepository;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public List<Movie> getMoviesByGenreName(String genreName) {
        final String key = "genre:" + genreName + ":movies";
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        List<Movie> moviesByGenres = (List<Movie>) valueOperations.get(key);
        if (moviesByGenres == null) {
//            Genre genre = genreRepository.findByGenreName(genreName);
//            List<GenreAndMovieDTO> genreAndMovieDTO = genreAndMovieRepository.findGenreAndMovieDTOByGenreId(genre.getId());
//            List<Long> tmdbIds = new ArrayList<>();
//            for (GenreAndMovieDTO dto : genreAndMovieDTO) {
//                tmdbIds.add(dto.getTmdbId());
//            }
//            moviesByGenres = movieRepository.findAllByTmdbIds(tmdbIds);
            moviesByGenres = genreRepository.findMoviesByGenreName(genreName);
            valueOperations.set(key, moviesByGenres, 1, TimeUnit.HOURS);
        }
        return moviesByGenres;
    }
}

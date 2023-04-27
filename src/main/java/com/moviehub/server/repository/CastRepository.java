package com.moviehub.server.repository;

import com.moviehub.server.entity.Cast;
import com.moviehub.server.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Project ：server
 * @File ：CastRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/27 15:26
 **/
public interface CastRepository extends JpaRepository<Cast, String> {
    List<Cast> findByMovieTmdbId(Long tmdbId);

    Cast findByCreditId(String creditId);

    List<Movie> findMoviesByTmdbId(Long tmdbId);
}

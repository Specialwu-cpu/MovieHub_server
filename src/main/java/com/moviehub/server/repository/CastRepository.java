package com.moviehub.server.repository;

import com.moviehub.server.entity.Cast;
import com.moviehub.server.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Project ：server
 * @File ：CastRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/27 15:26
 **/
public interface CastRepository extends JpaRepository<Cast, String> {
    @Query(value = "select * from cast where movie_tmdb_id = ?1", nativeQuery = true)
    List<Cast> findByMovieTmdbId(Long tmdbId);

    @Query(value = "select * from cast where credit_id = ?1", nativeQuery = true)
    Cast findByCreditId(String creditId);

    @Query(value = "select * from movie where tmdb_id = ?1", nativeQuery = true)
    List<Movie> findMoviesByTmdbId(Long tmdbId);

    List<Cast> findByTmdbId(Long tmdbId);
}

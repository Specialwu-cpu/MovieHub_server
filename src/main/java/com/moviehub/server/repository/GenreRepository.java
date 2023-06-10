package com.moviehub.server.repository;

import com.moviehub.server.entity.Genre;
import com.moviehub.server.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Project ：server
 * @File ：GenreRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/27 13:51
 **/
public interface GenreRepository extends JpaRepository<Genre, String> {
    @Query(value = "select * from genre where genre_name = ?1", nativeQuery = true)
    Genre findByGenreName(String genreName);

    @Query(value = "select * from genre where genre_id in (select genre_id from genre_and_movie where tmdb_id = ?1)", nativeQuery = true)
    List<Genre> findByTmdbId(Long tmdb_id);
    @Query(value = "select * from movie where tmdb_id in (select tmdb_id from genre_and_movie where genre_id in (select genre_id from genre where genre_name = ?1))", nativeQuery = true)
    List<Movie> findMoviesByGenreName(String genreName);
}

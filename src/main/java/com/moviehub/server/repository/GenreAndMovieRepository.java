package com.moviehub.server.repository;

import com.moviehub.server.dto.GenreAndMovieDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Project ：server
 * @File ：GenreAndMovieRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/27 13:43
 **/
public interface GenreAndMovieRepository extends JpaRepository<GenreAndMovieDTO, String> {
    @Query(value = "select * from genre_and_movie where genre_id = ?1", nativeQuery = true)
    List<GenreAndMovieDTO> findGenreAndMovieDTOByGenreId(int genreId);
}

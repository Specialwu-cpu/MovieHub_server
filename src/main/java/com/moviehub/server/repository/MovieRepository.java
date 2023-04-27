package com.moviehub.server.repository;

import com.moviehub.server.entity.Movie;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Project ：MovieHub-server
 * @File ：MovieRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/25 11:35
 **/
public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query(value = "select * from movie order by popularity desc limit 0, 19", nativeQuery = true)
    List<Movie> findMovieMostPopular();

    @Query(value = "select * from movie order by revenue desc limit 0, 19", nativeQuery = true)
    List<Movie> findMovieMostEarn();

    @Query(value = "SELECT * FROM movie WHERE MONTH(release_date) = MONTH(CURRENT_DATE()) AND DAY(release_date) = DAY(CURRENT_DATE()) ORDER BY popularity DESC LIMIT 0, 19", nativeQuery = true)
    List<Movie> findTodayMovie();


    @Query(value = "select * from movie where original_language = 'cn' order by " +
            "popularity desc limit ?1 offset ?2", nativeQuery = true)
    List<Movie> findForRecommendGuest(int size, int offset);

    @Query(value = "SELECT * FROM movie m WHERE m.tmdb_id IN :tmdbIds order by popularity desc limit 0, 19", nativeQuery = true)
    List<Movie> findAllByTmdbIds(@Param("tmdbIds") List<Long> tmdbIds);

//    List<Movie> findAllByTmdbIdIn(List<Long> tmdbIds);
}

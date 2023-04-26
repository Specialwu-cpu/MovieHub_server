package com.moviehub.server.repository;

import com.moviehub.server.DTO.MovieShowed;
import com.moviehub.server.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * @Project ：MovieHub-server
 * @File ：MovieRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/25 11:35
 **/
public interface MovieRepository extends JpaRepository<Movie, String> {
    @Query(value = "select * from movie order by popularity desc limit 0, 19", nativeQuery = true)
    List<Movie> findMovieMostPopular();

    @Query(value = "select * from movie order by revenue desc limit 0, 19", nativeQuery = true)
    List<Movie> findMovieMostEarn();

    @Query(value = "SELECT * FROM movie WHERE MONTH(release_date) = MONTH(CURRENT_DATE()) AND DAY(release_date) = DAY(CURRENT_DATE()) ORDER BY popularity DESC LIMIT 0, 19", nativeQuery = true)
    List<Movie> findTodayMovie();

}

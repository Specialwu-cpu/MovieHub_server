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

    Movie findByTmdbId(Long tmdbId);


    @Query(value = "select * from movie where original_language = 'cn' order by " +
            "popularity desc limit ?1 offset ?2", nativeQuery = true)
    List<Movie> findForRecommendGuest(int size, int offset);

    @Query(value = "SELECT * FROM movie m WHERE m.tmdb_id IN :tmdbIds order by popularity desc limit 0, 19", nativeQuery = true)
    List<Movie> findAllByTmdbIds(@Param("tmdbIds") List<Long> tmdbIds);

//    @Query(value = "select * from movie where tmdb_id in (select tmdb_id from genre_and_movie where genre_id = (select genre_id from genre where genre_name = ?1))", nativeQuery = true)
//    List<Movie> findMoviesByGenreName(String genreName);

//    List<Movie> findAllByTmdbIdIn(List<Long> tmdbIds);
    @Query(value = "select * from movie where tmdb_id in (select tmdb_id from genre_and_movie where genre_id in (select genre_id from genre where genre_name = ?1)) ORDER BY popularity DESC LIMIT 0, 19", nativeQuery = true)
    List<Movie> findMoviesByGenreName(String genreName);

    @Query(value = "select * from movie where tmdb_id = ?1", nativeQuery = true)
    List<Movie> findMoviesByCast(Long tmdbId);

    @Query(value = "select * from movie where tmdb_id = ?1", nativeQuery = true)
    List<Movie> findMoviesByCrew(Long tmdbId);

    @Query(value = "SELECT * FROM movie m WHERE m.tmdb_id IN (SELECT tmdb_id FROM user_history WHERE mail_or_id = ?1)", nativeQuery = true)
    List<Movie> findMoviesByHistoryId(String mailOrId, int size, int offset);

    @Query(value = "SELECT * FROM movie m WHERE m.tmdb_id IN (SELECT tmdb_id FROM user_collection WHERE mail_or_id = ?1)", nativeQuery = true)
    List<Movie> findMoviesByCollectionId(String mailOrId, int size, int offset);

    @Query(value = "SELECT * FROM movie m WHERE LOWER(m.title) LIKE %:query% limit 0, 10", nativeQuery = true)
    List<Movie> findByTitleContainingIgnoreCase(String query);

    @Query(value = "SELECT * FROM movie m WHERE m.tmdb_id IN (SELECT subquery.tmdb_id FROM (SELECT tmdb_id FROM keyword_and_movie WHERE keyword_id IN (SELECT keyword_id FROM keyword k WHERE LOWER(k.keyword_name) LIKE %:query%) limit 10) AS subquery )", nativeQuery = true)
    List<Movie> findByKeywordsContainingIgnoreCase(String query);

    @Query(value = "SELECT * FROM movie m WHERE m.tmdb_id IN (SELECT subquery.tmdb_id FROM (SELECT tmdb_id FROM crew WHERE credit_id IN (SELECT credit_id FROM credit c WHERE LOWER(c.name) LIKE %:query%) limit 5 )AS subquery)", nativeQuery = true)
    List<Movie> findByCrewContainingIgnoreCase(String query);

    @Query(value = "SELECT * FROM movie m WHERE m.tmdb_id IN (SELECT subquery.tmdb_id FROM (SELECT tmdb_id FROM cast WHERE credit_id IN (SELECT credit_id FROM credit c WHERE LOWER(c.name) LIKE %:query%) limit 5 )AS subquery)", nativeQuery = true)
    List<Movie> findByCastContainingIgnoreCase(String query);

    @Query(value = "SELECT * FROM movie m WHERE m.tmdb_id IN (SELECT subquery.tmdb_id FROM (SELECT tmdb_id FROM keyword_and_movie WHERE keyword_id = ?1 limit 5) AS subquery )", nativeQuery = true)
    List<Movie> findMoviesByKeywordId(Integer keywordId);
    @Query(value = "SELECT m.tmdb_id FROM movie m", nativeQuery = true)
    List<Long> findAllTmdbIds();
}

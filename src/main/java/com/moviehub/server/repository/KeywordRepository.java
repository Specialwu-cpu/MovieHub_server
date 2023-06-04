package com.moviehub.server.repository;

import com.moviehub.server.entity.Genre;
import com.moviehub.server.entity.Keyword;
import com.moviehub.server.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, String> {

    @Query(value = "select * from keyword where keyword_id in (select keyword_id from keyword_and_movie where tmdb_id = ?1)", nativeQuery = true)
    List<Keyword> findByTmdbId(Long tmdb_id);
}

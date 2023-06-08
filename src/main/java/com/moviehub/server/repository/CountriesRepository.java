package com.moviehub.server.repository;

import com.moviehub.server.entity.Cast;
import com.moviehub.server.entity.Countries;
import com.moviehub.server.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountriesRepository extends JpaRepository<Countries, String> {

    @Query(value = "select * from countries where country_abbreviate in (select country_abbreviate from country_and_movie where tmdb_id = ?1)", nativeQuery = true)
    List<Countries> findByTmdbId(Long tmdb_id);
}

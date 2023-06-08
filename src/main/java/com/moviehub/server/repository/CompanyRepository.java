package com.moviehub.server.repository;

import com.moviehub.server.entity.Company;
import com.moviehub.server.entity.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, String> {
    @Query(value = "select * from company where company_id in (select companies_id from com_and_mov where tmdb_id = ?1)", nativeQuery = true)
    List<Company> findByTmdbId(Long tmdb_id);
}

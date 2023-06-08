package com.moviehub.server.repository;

import com.moviehub.server.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Project ：server
 * @File ：CrewRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/27 15:25
 **/
public interface CrewRepository extends JpaRepository<Crew, String> {
    @Query(value = "select * from crew where credit_id = ?1", nativeQuery = true)
    Crew findByCreditId(String creditId);
}

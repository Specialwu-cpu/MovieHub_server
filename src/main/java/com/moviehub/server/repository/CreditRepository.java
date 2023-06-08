package com.moviehub.server.repository;

import com.moviehub.server.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Project ：server
 * @File ：CreditRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/27 15:26
 **/
public interface CreditRepository extends JpaRepository<Credit, String> {
    @Query(value = "select * from credit where credit_id = ?1", nativeQuery = true)
    Credit findByCreditId(String creditId);
}

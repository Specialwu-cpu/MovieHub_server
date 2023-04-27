package com.moviehub.server.repository;

import com.moviehub.server.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project ：server
 * @File ：CreditRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/27 15:26
 **/
public interface CreditRepository extends JpaRepository<Credit, String> {
}

package com.moviehub.server.repository;

import com.moviehub.server.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project ：MovieHub-server
 * @File ：MovieRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/4/25 11:35
 **/
public interface MovieRepository extends JpaRepository<Movie, String> {
}

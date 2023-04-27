package com.moviehub.server.repository;

import com.moviehub.server.entity.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project ：server
 * @File ：CrewRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/27 15:25
 **/
public interface CrewRepository extends JpaRepository<Crew, String> {
}

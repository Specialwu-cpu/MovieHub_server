package com.moviehub.server.repository;

import com.moviehub.server.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Project ：server
 * @File ：UserRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/3/15 10:01 **/

public interface UserRepository extends JpaRepository<user, String> {
}
// long is the same as Long in entity

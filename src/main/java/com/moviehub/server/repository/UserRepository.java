package com.moviehub.server.repository;

import com.moviehub.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project ：server
 * @File ：UserRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/3/15 10:01 **/
public interface UserRepository extends JpaRepository<User, Long> {
}
// long is the same as Long in entity

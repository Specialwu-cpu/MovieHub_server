package com.moviehub.server.repository;

import com.moviehub.server.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project ：server
 * @File ：UserRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/3/15 10:01 **/

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
// long is the same as Long in entity

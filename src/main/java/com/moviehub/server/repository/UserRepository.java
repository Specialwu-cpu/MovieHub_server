package com.moviehub.server.repository;

import com.moviehub.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Project ：server
 * @File ：UserRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/3/15 10:01 **/

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "select * from user where mail_or_id=?1 and password=?2", nativeQuery = true)
    List<User> findByMailOrIdAndPassword(String mail_or_id, String password);
}
// long is the same as Long in entity

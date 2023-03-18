package com.moviehub.server.repository;

import com.moviehub.server.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Project ：server
 * @File ：UserRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/3/15 10:01 **/

@Repository
public interface UserRepository extends JpaRepository<user, String> {
    @Query(value = "select * from user where mail_or_id=?1 and password=?2", nativeQuery = true)
    List<user> findByMail_or_idAndPassword(String mail_or_id, String password);
}
// long is the same as Long in entity

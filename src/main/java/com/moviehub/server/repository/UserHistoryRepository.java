package com.moviehub.server.repository;

import com.moviehub.server.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project ：server
 * @File ：UserHistoryRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/6/9 10:43
 **/
@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, String> {
}

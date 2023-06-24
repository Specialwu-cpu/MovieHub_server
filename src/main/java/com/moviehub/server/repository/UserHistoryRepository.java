package com.moviehub.server.repository;

import com.moviehub.server.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Project ：server
 * @File ：UserHistoryRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/6/9 10:43
 **/
@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {
    @Query(value = "select * from user_history where mail_or_id=?1 and tmdb_id=?2", nativeQuery = true)
    UserHistory findByMailOrIdAndTmdbId(String mailOrId, Long tmdbId);
}

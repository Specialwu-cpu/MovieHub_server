package com.moviehub.server.repository;

import com.moviehub.server.entity.UserCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project ：server
 * @File ：UserCollectionRepository.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh
 * @Date ：2023/6/9 10:44
 **/
@Repository
public interface UserCollectionRepository extends JpaRepository<UserCollection, Integer> {
}

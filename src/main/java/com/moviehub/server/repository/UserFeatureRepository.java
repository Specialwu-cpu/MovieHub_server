package com.moviehub.server.repository;

import com.moviehub.server.entity.Rating;
import com.moviehub.server.entity.UserFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeatureRepository extends JpaRepository<UserFeature, String> {

    @Modifying
    @Query(value = "update user_feature set genre_?1 = genre_?1 + 1 where mail_or_id = ?2", nativeQuery = true)
    int updateGenreN(int genreId, String email);
}

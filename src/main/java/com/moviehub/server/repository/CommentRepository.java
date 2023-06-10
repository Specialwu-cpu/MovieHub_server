package com.moviehub.server.repository;

import com.moviehub.server.entity.Comment;
import com.moviehub.server.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {


    @Query(value = "select * from comment where tmdb_id = ?1", nativeQuery = true)
    List<Comment> findByTmdbId(Long tmdb_id);

    @Modifying
    @Query(value = "insert into comment value(?1, ?2, ?3, ?4)", nativeQuery = true)
    int insertNewComment(String mailOrId, Long tmdbId, String text, Timestamp timestamp);
}

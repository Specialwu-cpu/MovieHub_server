package com.moviehub.server.repository;

import com.moviehub.server.entity.Movie;
import com.moviehub.server.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
}

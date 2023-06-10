package com.moviehub.server.service;

import com.moviehub.server.util.BaseResponse;

public interface ISingleMovieService {


    BaseResponse getSingleMovie(Long tmdb_id);

    BaseResponse rateSingleMovie(String email, Long tmdb_id, float rate);
}

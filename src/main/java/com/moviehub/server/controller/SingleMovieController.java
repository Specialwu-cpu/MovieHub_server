package com.moviehub.server.controller;

import com.moviehub.server.service.ISingleMovieService;
import com.moviehub.server.util.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/singleMovie")
@Tag(name = "SingleMovieController", description = "SingleMovieController")
public class SingleMovieController {
    @Resource
    private ISingleMovieService singleMovieService;

    @GetMapping("/searchMovie")
    public BaseResponse getSingleMovie(@RequestParam(value = "tmdb_id", defaultValue = "862") Long tmdb_id) {
        return singleMovieService.getSingleMovie(tmdb_id);
    }
}

package com.moviehub.server.controller;

import com.moviehub.server.service.ISingleMovieService;
import com.moviehub.server.util.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/singleMovie")
@Tag(name = "SingleMovieController", description = "SingleMovieController")
@CrossOrigin(origins = "*")
public class SingleMovieController {
    @Resource
    private ISingleMovieService singleMovieService;

    @GetMapping("/searchMovie")
    public BaseResponse getSingleMovie(@RequestParam(value = "tmdb_id", defaultValue = "862") Long tmdb_id) {
        return singleMovieService.getSingleMovie(tmdb_id);
    }
}

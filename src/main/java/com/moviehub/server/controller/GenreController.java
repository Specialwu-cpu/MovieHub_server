package com.moviehub.server.controller;

import com.moviehub.server.entity.Movie;
import com.moviehub.server.service.IGenreService;
import com.moviehub.server.util.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project ：server
 * @File ：GenreController.java
 * @IDE ：IntelliJ IDEA
 * @Author ：wsh ruan
 * @Date ：2023/4/27 13:47
 **/
@RestController
@RequestMapping("/genre")
@Tag(name = "GenreController", description = "Controller for movie genres")
@CrossOrigin(origins = "*")
public class GenreController {

    @Resource
    private IGenreService genreService;

    @GetMapping("/{genreName}")
    @Operation(summary = "Get movies by genre name")
    public BaseResponse<List<Movie>> getMoviesByGenreName(
            @PathVariable("genreName") String genreName) {
        List<Movie> movies = genreService.getMoviesByGenreName(genreName);
        return BaseResponse.success(movies);
    }
}

